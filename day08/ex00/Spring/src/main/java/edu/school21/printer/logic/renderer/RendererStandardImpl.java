package edu.school21.printer.logic.renderer;

import edu.school21.printer.logic.preprocessor.PreProcessor;

public class RendererStandardImpl implements Renderer {

    PreProcessor preProcessor;

    public RendererStandardImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void outResult(String str) {
        System.out.println(preProcessor.preProcessesMessages(str));
    }
}
