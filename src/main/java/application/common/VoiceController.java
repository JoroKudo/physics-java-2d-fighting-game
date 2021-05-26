package application.common;


import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Port;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VoiceController implements Controller {

    private final Logger logger = Logger.getLogger(getClass().getName());
    private final ExecutorService eventsExecutorService = Executors.newFixedThreadPool(2);
    public Configuration configuration = new Configuration();
    private LiveSpeechRecognizer recognizer;
    private String speechRecognitionResult = "stop";
    private boolean speechRecognizerThreadRunning = false;

    public void initiate(Configuration configuration) {
        System.out.println("Loading Speech Controller...");
        configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
        configuration.setGrammarPath("resource:/grammars");
        configuration.setGrammarName("grammar");
        configuration.setUseGrammar(true);
        try {
            recognizer = new LiveSpeechRecognizer(configuration);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        startResourcesThread();
        startSpeechRecognition();
    }


    public synchronized void startSpeechRecognition() {


        eventsExecutorService.submit(() -> {

            speechRecognizerThreadRunning = true;
            recognizer.startRecognition(true);
            System.out.println("You can start to speak...");
            while (speechRecognizerThreadRunning) {
                SpeechResult speechResult = recognizer.getResult();
                speechRecognitionResult = speechResult.getHypothesis();
                if (speechRecognitionResult.equals("<unk>")) {
                    System.out.println("Sorry i didnt catch that");
                } else {
                    System.out.println("You said: [" + speechRecognitionResult + "]\n");
                }
            }
        });
    }


    public void startResourcesThread() {
        eventsExecutorService.submit(() -> {
            try {
                if (!AudioSystem.isLineSupported(Port.Info.MICROPHONE))
                    System.out.println("Microphone is not available.");
                Thread.sleep(350);
            } catch (InterruptedException ex) {
                logger.log(Level.WARNING, null, ex);
            }
        });
    }

    @Override
    public ActionType FighterXisActing(int id) {
        if (speechRecognitionResult.equals("jump")) {
            return ActionType.JUMP;
        }
        if (speechRecognitionResult.equals("left")) {
            return ActionType.WALK_lEFT;
        }
        if (speechRecognitionResult.equals("right")) {
            return ActionType.WALK_RIGHT;
        }
        if (speechRecognitionResult.equals("punch")) {
            return ActionType.PUNCH;
        }
        if (speechRecognitionResult.equals("duck")) {
            return ActionType.DUCK;
        }
        if (speechRecognitionResult.equals("stop")) {
            return null;
        }
        if (speechRecognitionResult.equals("block")) {
            return ActionType.BLOCK;
        }
        if (speechRecognitionResult.equals("ha do ken")) {
            return ActionType.HADOKEN;
        }
        return null;
    }

}

