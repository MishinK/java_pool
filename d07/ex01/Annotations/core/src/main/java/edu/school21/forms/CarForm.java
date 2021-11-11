package edu.school21.forms;

import edu.school21.annotations.*;


@HtmlForm(fileName = "car_form.html", action = "/car", method = "post")
public class CarForm {
    @HtmlInput(type = "text", name = "model", placeholder = "Enter Model")
    private String model;

    @HtmlInput(type = "text", name = "сolour", placeholder = "Enter Colour")
    private String сolour;

    @HtmlInput(type = "number", name = "mileage", placeholder = "Enter Mileage")
    private Integer mileage;
}