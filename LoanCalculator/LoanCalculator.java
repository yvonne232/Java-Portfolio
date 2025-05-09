/**
 * Name: Ziyu(Yvonne) Lin
 * Mini-project 3
 * EN.605.201.81.FA24
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * This class is a loan calculator that allows users to calculate their payments using the provided interface
 */
public class LoanCalculator extends Application
{
    // set up text field
    private TextField annualInterestRateField = new TextField();
    private TextField numberOfYearsField = new TextField();
    private TextField loanAmountField = new TextField();
    private TextField monthlyPaymentField = new TextField();
    private TextField totalPaymentField = new TextField();

    /**
     * This start method is to have a GUI interface which consists of labels, text fields, and a calculation button
     * @param stage the primary stage for the application
     */
    @Override
    public void start(Stage stage)
    {
        stage.setTitle("Loan Calculator"); // Set GUI title

        // Set up grid pane
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        // Add labels and text fields
        gridPane.add(new Label("Annual Interest Rate:"), 0, 0);
        gridPane.add(annualInterestRateField,1,0);
        gridPane.add(new Label("Number of Years:"), 0, 1);
        gridPane.add(numberOfYearsField, 1, 1);
        gridPane.add(new Label("Loan Amount:"), 0, 2);
        gridPane.add(loanAmountField, 1, 2);
        gridPane.add(new Label("Monthly Payment:"), 0, 3);
        gridPane.add(monthlyPaymentField, 1, 3);
        gridPane.add(new Label("Total Payment:"), 0, 4);
        gridPane.add(totalPaymentField, 1, 4);

        // Set button
        Button calculateButton = new Button("Calculate");
        gridPane.add(calculateButton, 1, 5);
        GridPane.setHalignment(calculateButton, javafx.geometry.HPos.RIGHT); // Align button with the text fields to the right

        // Set action for calculate button
        calculateButton.setOnAction(event -> calculateLoanPayment());

        // Create Scene
        Scene scene = new Scene(gridPane, 400, 300);
        stage.setScene(scene); // Set the scene on the stage
        stage.show();

    }

    /**
     * This is for calculating monthly/total loan payment using user input annual interest rate, number of years and loan payment
     */
    private void calculateLoanPayment()
    {
        // Use a try catch logic to catch invalid user input
        try
        {
            // Get annual interest rate, number of years and loan amount from user input
            double annualInterestRate = Double.parseDouble(annualInterestRateField.getText());
            double numberOfYears = Double.parseDouble(numberOfYearsField.getText());
            double loanAmount = Double.parseDouble(loanAmountField.getText());

            // Calculate monthly payment and total payment
            double monthlyInterestRate = annualInterestRate / 12; // If interest is compounded monthly, convert annual interest rate to monthly interest rate
            double numberOfPayments = numberOfYears * 12;
            double monthlyPayment = loanAmount * monthlyInterestRate / (1 - Math.pow(1 + monthlyInterestRate, -numberOfPayments));
            double totalPayment = monthlyPayment * numberOfPayments;

            // Set monthlyPayment and totalPayment to the text fields
            monthlyPaymentField.setText(String.format("%.2f", monthlyPayment));
            totalPaymentField.setText(String.format("%.2f", totalPayment));

        } catch (Exception e)
        {
            // If it catches exception, set the text to invalid input
            monthlyPaymentField.setText("Invalid Input");
            totalPaymentField.setText("Invalid Input");
        }

    }

    /**
     * This main method is to launch the GUI
     * @param args command-line arguments passed to the application
     */
    public static void main(String[] args)
    {
        launch();
    }
}
