package com.pluralsight.dealershipversion2.service.commandpattern;

import com.pluralsight.dealershipversion2.entity.Dealer;

import java.util.Stack;

public class Dealership {

    private Dealer dealer;
    private static Dealership dealership;

    /*
    The Stack class represents a last-in-first-out (LIFO) stack of objects.
    It extends class Vector with five operations that allow a vector to be treated as a stack.
    The usual push and pop operations are provided, as well as a method to peek at the top item on the stack,
    a method to test for whether the stack is empty,
    and a method to search the stack for an item and discover how far it is from the top.
    When a stack is first created, it contains no i
     */
    private Dealership() {
        dealer = new Dealer();
    }

    public Dealership getInstance() {
        if (dealership != null) {
            return dealership;
        }
        dealership =  new Dealership();
        return dealership;
    }

    private final Stack<Command> commandHistory = new Stack<>();

    public void executeCommand(Command command) {
        command.execute();
        commandHistory.push(command);
    }

    public void undoLastCommand() {
        if (!commandHistory.isEmpty()) {
            Command lastCommand = commandHistory.pop();
            lastCommand.undo();
        }
    }

}
