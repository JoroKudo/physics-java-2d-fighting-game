package application.common;


import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.result.WordResult;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Port;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VoiceContrroll implements Controller {

    private LiveSpeechRecognizer recognizer;

    private Logger logger = Logger.getLogger(getClass().getName());


    private String speechRecognitionResult = "stop";


    private boolean ignoreSpeechRecognitionResults = false;

    private boolean speechRecognizerThreadRunning = false;



    private ExecutorService eventsExecutorService = Executors.newFixedThreadPool(2);
    public Configuration configuration = new Configuration();


    public void initiate(Configuration configuration) {
        logger.log(Level.INFO, "Loading Speech Recognizer...\n");


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
            return ActionType.HADOUKEN;
        }

        return null;
    }

    public synchronized void startSpeechRecognition() {


            eventsExecutorService.submit(() -> {

                speechRecognizerThreadRunning = true;
                ignoreSpeechRecognitionResults = false;

                recognizer.startRecognition(true);

                logger.log(Level.INFO, "You can start to speak...\n");

                try {
                    while (speechRecognizerThreadRunning) {

                        SpeechResult speechResult = recognizer.getResult();

                        if (!ignoreSpeechRecognitionResults) {

                            if (speechResult == null)
                                logger.log(Level.INFO, "I can't understand what you said.\n");
                            else {

                                speechRecognitionResult = speechResult.getHypothesis();

                                System.out.println("You said: [" + speechRecognitionResult + "]\n");

                                makeDecision(speechRecognitionResult, speechResult.getWords());

                            }
                        }

                    }
                } catch (Exception ex) {
                    logger.log(Level.WARNING, null, ex);
                    speechRecognizerThreadRunning = false;
                }

                logger.log(Level.INFO, "SpeechThread has exited...");

            });
    }


    public void startResourcesThread() {


            eventsExecutorService.submit(() -> {
                try {



                    while (true) {

                        if (!AudioSystem.isLineSupported(Port.Info.MICROPHONE))
                            logger.log(Level.INFO, "Microphone is not available.\n");

                        Thread.sleep(350);
                    }

                } catch (InterruptedException ex) {
                    logger.log(Level.WARNING, null, ex);
                }
            });
    }


    public void makeDecision(String speech, List<WordResult> speechWords) {

        System.out.println(speech);

    }


}