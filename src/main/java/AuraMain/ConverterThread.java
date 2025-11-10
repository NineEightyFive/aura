package AuraMain;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

import ws.schild.jave.Encoder;
import ws.schild.jave.EncoderException;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;

import java.nio.file.Path;

public class ConverterThread extends SwingWorker<Void, Integer> {

    private final ArrayList<EMAudioFile> converterQueue;
    private final JProgressBar progBar;
    private final JButton cancel;

    public ConverterThread(ArrayList<EMAudioFile> list, JProgressBar pB,JButton cB) {
        converterQueue = list;
        progBar = pB;
        cancel = cB;

        for (ActionListener al : cB.getActionListeners()) {
            cB.removeActionListener(al);
        }

    }

    @Override
    protected Void doInBackground() throws Exception {
        int total =  converterQueue.size();
        int filesDone = 0;

        for (EMAudioFile af : converterQueue) {
            if(isCancelled()) break;

            try {



                File source = new File(af.getPath());
                File target = ConvertEngine.getTargetSrc(source,af,false);

                //System.out.println(getOldFormat(af));
                //System.out.println(af.getNewFormat());
                //System.out.println(!getOldFormat(af).equals(af.getNewFormat()));

                if(af.getNewFormat()!=null && !ConvertEngine.getOldFormat(af).equals(af.getNewFormat())) { // Used to check if file formats are same, only metadata changes?
                    //boolean DuplicateProtocol = false;

                    String DC = ConvertEngine.DuplicateCheck(target,af);
                    if(DC.equals("Skip")) {
                        continue;

                    } else if(DC.equals("Rename")) {
                        //DuplicateProtocol = true;
                        target = ConvertEngine.getTargetSrc(source,af,true);
                    }

                    //Audio Attributes
                    AudioAttributes audio = new AudioAttributes();

                    //Encoding attributes
                    EncodingAttributes attrs = new EncodingAttributes();
                    attrs.setOutputFormat(af.getNewFormat());
                    attrs.setAudioAttributes(audio);
                    attrs.setVideoAttributes(null);

                    //Encodes file
                    Encoder encoder = new Encoder();
                    encoder.encode(new MultimediaObject(source), target, attrs);
                } // End Conversion Changes
                // Metadata Changes
                System.out.println("Attempting to set metadata...");
                af.getMetaLink().applyData(source,target);
                System.out.println("Set MetaData successfully");
                filesDone++;
                int progress = (int) ((filesDone / (double) total) * 100);
                publish(progress);
                setProgress(progress);
            } catch(EncoderException e) {

                UI.sendNotification("err","Error when converting file: "+e);

            }
        }
        return null;
    }

    @Override
    protected void process(List<Integer> chunk) {
        int latProgress = chunk.get(chunk.size()-1);
        progBar.setValue(latProgress);
    }

    @Override
    protected void done() {
        cancel.setEnabled(false);
        if(isCancelled()) {
            UI.sendNotification("gen","Conversion Cancelled by User");
        } else {
            UI.sendNotification("gen","Conversion Successful!");
        }
    }

}
