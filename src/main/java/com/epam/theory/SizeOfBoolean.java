package com.epam.theory;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

public class SizeOfBoolean {

    public static void main(String[] args) {
        System.out.println(VM.current().details());

        System.out.println(ClassLayout.parseClass(boolean.class).toPrintable());

        boolean[] arr = new boolean[64];
        System.out.println(ClassLayout.parseInstance(arr));


        System.out.println(ClassLayout.parseClass(Boolean.class).toPrintable());
        Boolean[] refBooleans = new Boolean[64];
        System.out.println(ClassLayout.parseInstance(refBooleans));

    }
}
