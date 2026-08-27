import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Controller class for the Tip Calculator application.
 * Handles user interactions and calculations for the tip calculator.
 */
public class TipCalculatorController {
    
    @FXML
    private TextField billAmountField;
    
    @FXML
    private TextField tipPercentageField;
    
    @FXML
    private Label tipAmountLabel;
    
    @FXML
    private Label errorMessageLabel;
    
    @FXML
    private Button calculateButton;
    
    @FXML
    private Button resetButton;
    
    /**
     * Calculates the tip amount based on bill amount and tip percentage.
     * Displays the result or an error message accordingly.
     */
    @FXML
    private void calculateTip() {
        try {
            // Clear any previous error messages
            errorMessageLabel.setText("");
            
            // Get input values from text fields
            String billAmountStr = billAmountField.getText().trim();
            String tipPercentageStr = tipPercentageField.getText().trim();
            
            // Validate that fields are not empty
            if (billAmountStr.isEmpty() || tipPercentageStr.isEmpty()) {
                errorMessageLabel.setText("Error: Please enter both bill amount and tip percentage.");
                return;
            }
            
            // Parse input values
            double billAmount = Double.parseDouble(billAmountStr);
            double tipPercentage = Double.parseDouble(tipPercentageStr);
            
            // Validate that amounts are positive
            if (billAmount < 0 || tipPercentage < 0) {
                errorMessageLabel.setText("Error: Bill amount and tip percentage must be positive numbers.");
                return;
            }
            
            // Calculate tip amount
            double tipAmount = billAmount * (tipPercentage / 100.0);
            
            // Display the result formatted to 2 decimal places
            tipAmountLabel.setText(String.format("$%.2f", tipAmount));
            
        } catch (NumberFormatException e) {
            // Handle invalid number format
            errorMessageLabel.setText("Error: Invalid input. Please enter numbers only.");
            tipAmountLabel.setText("$0.00");
        } catch (Exception e) {
            // Handle any other unexpected exceptions
            errorMessageLabel.setText("Error: " + e.getMessage());
            tipAmountLabel.setText("$0.00");
        }
    }
    
    /**
     * Resets all fields and labels to their initial state.
     */
    @FXML
    private void reset() {
        billAmountField.clear();
        tipPercentageField.clear();
        tipAmountLabel.setText("$0.00");
        errorMessageLabel.setText("");
        
        // Optionally, set focus back to the first input field
        billAmountField.requestFocus();
    }
}
