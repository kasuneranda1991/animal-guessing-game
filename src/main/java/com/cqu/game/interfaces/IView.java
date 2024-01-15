/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cqu.game.interfaces;

/**
 * IView interface
 * 
 * this interface implemented on AnimalBehaviour Class
 * @author Madahapola Kankanamalage Kasun Eranda - 12216898
 */
public interface IView {

    public void display(String s);

    public void append(String s);

    public String ask(String question);

    public boolean choose(String question);

    public String choose(String question, String choice1, String choice2);
}
