package edu.school21.classes;

public class Car {
	
	private String model;
	private String colour;
	private Integer	mileage;

	public Car() {
		this.model = "BMW";
		this.colour = "black";
		this.mileage = 0;
	}

	public Car(String model, String colour, Integer	mileage) {
		this.model = model;
		this.colour = colour;
		this.mileage = mileage;
	}

	public void newColour(String colour) {
		this.colour = colour;
	}

	public Integer addMileage(Integer mileage) {
		this.mileage += mileage;
		return this.mileage;
	}

	@Override
    public String toString() {
        return "Car[" +
                "model='" + model + '\'' +
                ", colour='" + colour + '\'' +
				", mileage=" + mileage +
                ']';
    }
}
