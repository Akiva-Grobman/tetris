package com.akiva.keyboardinput;

public interface Subscribable {

    Runnable getOnExit();

    Runnable getOnUpClick();

    Runnable getOnDownClick();

    Runnable getOnRightClick();

    Runnable getLeftClick();

}
