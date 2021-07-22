package edu.school21.printer.logic.renderer;

import edu.school21.printer.logic.preprocessor.PreProcessor;

public class RendererErrImpl implements Renderer{

    PreProcessor processor;

    public RendererErrImpl(PreProcessor processor) {
        this.processor = processor;
    }

    @Override
    public void outResult(String str) {
        System.err.println(processor.preProcessesMessages(str));
    }
}
