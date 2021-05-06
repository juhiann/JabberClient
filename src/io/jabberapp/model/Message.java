package io.jabberapp.client.model;

import io.jabberapp.client.controller.MessageValidator;

import java.io.Serializable;

/**
 * @author Juhi Jose
 * This class defines the message protol object
 * Each Message object has a prefix and data part.
 * The output is a contactination of the prefix and data e.g. prefix[space]data
 */
public class Message implements Serializable
{
    private String prefix;
    private String data;
    private String output;
    private static final String [] protocol =
            {
                    "signin",
                    "signedin"
            };

    /** Constructors */

    public Message() { }

    public Message(String prefix, String data)
    {
        this.prefix = prefix;
        this.data = data;
        this.output = MessageValidator.combinePrefixAndSuffix(prefix, data, Message.protocol);
    }

    /** Getters */

    public String getPrefix() {
        return prefix;
    }

    public String getData() {
        return data;
    }

    public String getOutput() {
        return output;
    }

    /** Setters */

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}
