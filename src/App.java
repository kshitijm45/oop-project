import java.util.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

class UserAuthentication {
    private String username;
    private String password;

    // Constructor to initialize the username and password
    public UserAuthentication(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Method to authenticate the user
    public boolean authenticateUser(String enteredUsername, String enteredPassword) {
        return username.equals(enteredUsername) && password.equals(enteredPassword);
    }
}

class FinancialManager {
    private ArrayList<Double> incomes;
    private ArrayList<Double> expenses;

    // Constructor to initialize income and expense lists
    public FinancialManager() {
        this.incomes = new ArrayList<>();
        this.expenses = new ArrayList<>();
    }

    // Method to record income
    public void recordIncome(double amount) {
        incomes.add(amount);
        System.out.println("Income recorded successfully: $" + amount);
    }

    // Method to record expense
    public void recordExpense(double amount) {
        expenses.add(amount);
        System.out.println("Expense recorded successfully: $" + amount);
    }

    // Method to display income and expense summary
    public void displaySummary() {
        double totalIncome = incomes.stream().mapToDouble(Double::doubleValue).sum();
        double totalExpense = expenses.stream().mapToDouble(Double::doubleValue).sum();

        System.out.println("Total Income: $" + totalIncome);
        System.out.println("Total Expense: $" + totalExpense);
        System.out.println("Balance: $" + (totalIncome - totalExpense));
    }
}

class Transaction {
    private Date date;
    private String description;
    private double amount;

    // Constructor to initialize a transaction
    public Transaction(String description, double amount) {
        this.date = new Date();
        this.description = description;
        this.amount = amount;
    }

    // Method to display transaction details
    public void displayTransaction() {
        System.out.println("Date: " + date);
        System.out.println("Description: " + description);
        System.out.println("Amount: $" + amount);
    }
}

class TransactionHistoryManager {
    private ArrayList<Transaction> transactions;

    // Constructor to initialize the transaction history list
    public TransactionHistoryManager() {
        this.transactions = new ArrayList<>();
    }

    // Method to record a transaction
    public void recordTransaction(String description, double amount) {
        Transaction transaction = new Transaction(description, amount);
        transactions.add(transaction);
        System.out.println("Transaction recorded successfully:");
        transaction.displayTransaction();
    }

    // Method to display transaction history
    public void displayTransactionHistory() {
        System.out.println("Transaction History:");
        for (Transaction transaction : transactions) {
            transaction.displayTransaction();
            System.out.println("------------------------");
        }
    }
}

class BudgetManager {
    private Map<String, Double> budgets;

    // Constructor to initialize the budget map
    public BudgetManager() {
        this.budgets = new HashMap<>();
    }

    // Method to set a budget for a specific category
    public void setBudget(String category, double amount) {
        budgets.put(category, amount);
        System.out.println("Budget set successfully for " + category + ": $" + amount);
    }

    // Method to track spending against budgets
    public void trackSpending(String category, double spentAmount) {
        if (budgets.containsKey(category)) {
            double budgetAmount = budgets.get(category);
            double remainingBudget = budgetAmount - spentAmount;
            System.out.println("Remaining budget for " + category + ": $" + remainingBudget);
        } else {
            System.out.println("No budget set for " + category + ". Consider setting a budget.");
        }
    }
}

class FinancialReportGenerator {
    private ArrayList<Double> incomes;
    private ArrayList<Double> expenses;

    // Constructor to initialize income and expense lists
    public FinancialReportGenerator() {
        this.incomes = new ArrayList<>();
        this.expenses = new ArrayList<>();
    }

    // Method to generate an income vs. expense summary
    public void generateIncomeExpenseSummary() {
        double totalIncome = incomes.stream().mapToDouble(Double::doubleValue).sum();
        double totalExpense = expenses.stream().mapToDouble(Double::doubleValue).sum();

        System.out.println("Income vs. Expense Summary:");
        System.out.println("Total Income: $" + totalIncome);
        System.out.println("Total Expense: $" + totalExpense);
        System.out.println("Net Income: $" + (totalIncome - totalExpense));
    }

    // Method to generate category-wise spending report
    public void generateCategorySpendingReport() {
        // Implement logic to categorize expenses and display the report
        System.out.println("Category-wise Spending Report:");
        // ... (implementation for category-wise spending report)
    }

    // Method to generate budget comparison report
    public void generateBudgetComparisonReport() {
        // Implement logic to compare spending against budgets and display the report
        System.out.println("Budget Comparison Report:");
        // ... (implementation for budget comparison report)
    }
}

class SavingsManager {
    private Map<String, Double> savings;

    // Constructor to initialize the savings map
    public SavingsManager() {
        this.savings = new HashMap<>();
    }

    // Method to record a savings or investment account
    public void recordSavings(String accountName, double amount, double interestRate, String maturityDate) {
        savings.put(accountName, amount);
        System.out.println("Savings recorded successfully: " + accountName +
                " - Amount: $" + amount + ", Interest Rate: " + interestRate + "%, Maturity Date: " + maturityDate);
    }

    // Method to display savings and investment details
    public void displaySavings() {
        System.out.println("Savings and Investments:");
        for (Map.Entry<String, Double> entry : savings.entrySet()) {
            System.out.println(entry.getKey() + " - Amount: $" + entry.getValue());
        }
    }
}

class Goal {
    private String description;
    private double targetAmount;
    private double currentAmount;

    // Constructor to initialize a financial goal
    public Goal(String description, double targetAmount) {
        this.description = description;
        this.targetAmount = targetAmount;
        this.currentAmount = 0.0;
    }

    // Method to record progress towards a goal
    public void recordProgress(double amount) {
        currentAmount += amount;
        System.out.println("Progress recorded towards " + description + ": $" + amount);
    }

    // Method to check if the goal is achieved
    public boolean isGoalAchieved() {
        return currentAmount >= targetAmount;
    }

    // Method to display goal details
    public void displayGoalDetails() {
        System.out.println("Goal: " + description);
        System.out.println("Target Amount: $" + targetAmount);
        System.out.println("Current Amount: $" + currentAmount);
    }
}

class GoalManager {
    private Map<String, Goal> goals;

    // Constructor to initialize the goals map
    public GoalManager() {
        this.goals = new HashMap<>();
    }

    // Method to set a financial goal
    public void setGoal(String goalName, String description, double targetAmount) {
        Goal goal = new Goal(description, targetAmount);
        goals.put(goalName, goal);
        System.out.println("Financial goal set successfully: " + goalName);
    }

    // Method to record progress towards a goal
    public void recordProgress(String goalName, double amount) {
        if (goals.containsKey(goalName)) {
            Goal goal = goals.get(goalName);
            goal.recordProgress(amount);
            if (goal.isGoalAchieved()) {
                System.out.println("Congratulations! You've achieved your financial goal: " + goalName);
            }
        } else {
            System.out.println("No goal set with the name: " + goalName);
        }
    }
}

class ExpenseAnalytics {
    private ArrayList<Double> expenses;
    private Map<String, Double> categoryExpenses;

    // Constructor to initialize expense lists and category expenses
    public ExpenseAnalytics() {
        this.expenses = new ArrayList<>();
        this.categoryExpenses = new HashMap<>();
    }

    // Method to record an expense
    public void recordExpense(double amount, String category) {
        expenses.add(amount);

        // Update category-wise expenses
        if (categoryExpenses.containsKey(category)) {
            double currentCategoryExpense = categoryExpenses.get(category);
            categoryExpenses.put(category, currentCategoryExpense + amount);
        } else {
            categoryExpenses.put(category, amount);
        }

        System.out.println("Expense recorded successfully: $" + amount + " in category " + category);
    }

    // Method to view detailed analytics on spending habits
    public void viewExpenseAnalytics() {
        System.out.println("Expense Analytics:");
        System.out.println("Total Expenses: $" + expenses.stream().mapToDouble(Double::doubleValue).sum());

        System.out.println("Category-wise Expenses:");
        for (Map.Entry<String, Double> entry : categoryExpenses.entrySet()) {
            System.out.println(entry.getKey() + ": $" + entry.getValue());
        }
    }
}

class CurrencyConverter {
    private Map<String, Double> exchangeRates;

    // Constructor to initialize the exchange rates
    public CurrencyConverter() {
        this.exchangeRates = new HashMap<>();
    }

    // Method to set exchange rates
    public void setExchangeRate(String currencyCode, double rate) {
        exchangeRates.put(currencyCode, rate);
        System.out.println("Exchange rate set successfully for " + currencyCode + ": " + rate);
    }

    // Method to convert an amount from one currency to another
    public double convertCurrency(double amount, String fromCurrency, String toCurrency) {
        if (exchangeRates.containsKey(fromCurrency) && exchangeRates.containsKey(toCurrency)) {
            double fromRate = exchangeRates.get(fromCurrency);
            double toRate = exchangeRates.get(toCurrency);
            return amount * (toRate / fromRate);
        } else {
            System.out.println("Invalid currency codes. Please check the exchange rates.");
            return -1.0; // indicating an error
        }
    }
}

public class App extends Application {
    private UserAuthentication userAuth;
    private FinancialManager financialManager;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        userAuth = new UserAuthentication("johnDoe", "pass123");
        financialManager = new FinancialManager();

        primaryStage.setTitle("Personal Money Manager");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // User Authentication
        addAuthenticationControls(grid);

        // Financial Management
        addFinancialManagementControls(grid);

        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private void addAuthenticationControls(GridPane grid) {
        Label authLabel = new Label("User Authentication");
        grid.add(authLabel, 0, 0, 2, 1);

        Label usernameLabel = new Label("Username:");
        grid.add(usernameLabel, 0, 1);

        TextField usernameField = new TextField();
        grid.add(usernameField, 1, 1);

        Label passwordLabel = new Label("Password:");
        grid.add(passwordLabel, 0, 2);

        PasswordField passwordField = new PasswordField();
        grid.add(passwordField, 1, 2);

        Button authButton = new Button("Authenticate");
        grid.add(authButton, 1, 3);

        authButton.setOnAction(e -> {
            String enteredUsername = usernameField.getText();
            String enteredPassword = passwordField.getText();

            if (userAuth.authenticateUser(enteredUsername, enteredPassword)) {
                showAlert("Authentication Successful", "Access granted.");
            } else {
                showAlert("Authentication Failed", "Access denied.");
            }
        });
    }

    private void addFinancialManagementControls(GridPane grid) {
        Label financialLabel = new Label("Financial Management");
        grid.add(financialLabel, 0, 5, 2, 1);

        Label incomeLabel = new Label("Income:");
        grid.add(incomeLabel, 0, 6);

        TextField incomeField = new TextField();
        grid.add(incomeField, 1, 6);

        Label expenseLabel = new Label("Expense:");
        grid.add(expenseLabel, 0, 7);

        TextField expenseField = new TextField();
        grid.add(expenseField, 1, 7);

        Button recordButton = new Button("Record Transaction");
        grid.add(recordButton, 1, 8);

        recordButton.setOnAction(e -> {
            try {
                double incomeAmount = Double.parseDouble(incomeField.getText());
                double expenseAmount = Double.parseDouble(expenseField.getText());

                financialManager.recordIncome(incomeAmount);
                financialManager.recordExpense(expenseAmount);
                showAlert("Transaction Recorded", "Income: $" + incomeAmount + "\nExpense: $" + expenseAmount);
            } catch (NumberFormatException ex) {
                showAlert("Invalid Input", "Please enter valid numeric values for income and expense.");
            }
        });
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
}
