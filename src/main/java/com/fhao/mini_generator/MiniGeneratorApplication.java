package com.fhao.mini_generator;


import com.fhao.mini_generator.generate.AbstractGenerate;

public class MiniGeneratorApplication {

    public static void main(String[] args) {
        AbstractGenerate generate = AbstractGenerate.init();
        generate.execute();
    }

}
