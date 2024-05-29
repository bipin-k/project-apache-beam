package com.github;

import lombok.extern.slf4j.Slf4j;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;

import java.util.Arrays;

@Slf4j
public class MultiplyNumberByTwo {

    public static void main(String[] args) {
        PipelineOptions options = PipelineOptionsFactory.fromArgs(args).withValidation().create();

        // Create the pipeline
        Pipeline pipeline = Pipeline.create(options);

        // Define input data
        final String[] inputData = {"1", "2", "3", "4", "5"};

        // Apply the pipeline transformation
        pipeline
                .apply("Create Input", Create.of(Arrays.asList(inputData)))
                .apply("Convert to Integer", ParDo.of(new StringToIntegerFn()))
                .apply("Multiply by 2", ParDo.of(new MultiplyBy2Fn()))
                .apply("Convert to String", ParDo.of(new IntegerToStringFn()))
                .apply("Log Results", ParDo.of(new LogResultsFn()));

        // Run the pipeline
        pipeline.run();
    }

    static class StringToIntegerFn extends DoFn<String, Integer> {
        @ProcessElement
        public void processElement(ProcessContext c) {
            Integer number = Integer.parseInt(c.element());
            c.output(number);
        }
    }

    static class MultiplyBy2Fn extends DoFn<Integer, Integer> {
        @ProcessElement
        public void processElement(ProcessContext c) {
            Integer number = c.element() * 2;
            c.output(number);
        }
    }

    static class IntegerToStringFn extends DoFn<Integer, String> {
        @ProcessElement
        public void processElement(ProcessContext c) {
            String result = String.valueOf(c.element());
            c.output(result);
        }
    }

    static class LogResultsFn extends DoFn<String, Void> {
        @ProcessElement
        public void processElement(ProcessContext c) {
            String result = c.element();
            log.info("Result: {}", result);
        }
    }
}
