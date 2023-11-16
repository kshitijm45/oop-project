import java.util.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.text.SimpleDateFormat;

interface UserAuth{
    boolean authenticateUser(String username, String password);
}

class UserAuthentication implements UserAuth{
    private String username;
    private String password;

    public UserAuthentication(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean authenticateUser(String enteredUsername, String enteredPassword) {
        return username.equals(enteredUsername) && password.equals(enteredPassword);
    }
}

class FinancialManager {
    private ArrayList<Double> incomes;
    private ArrayList<Double> expenses;

    public FinancialManager() {
        this.incomes = new ArrayList<>();
        this.expenses = new ArrayList<>();
    }

    public String recordIncome(double amount) {
        incomes.add(amount);
        String message = "Income recorded successfully: $" + amount;
        System.out.println(message);
        return message;
    }

    public String recordExpense(double amount) {
        expenses.add(amount);
        String message = "Expense recorded successfully: $" + amount;
        System.out.println(message);
        return message;
    }

    public String displaySummary() {
    double totalIncome = incomes.stream().mapToDouble(Double::doubleValue).sum();
    double totalExpense = expenses.stream().mapToDouble(Double::doubleValue).sum();

    String summary = "Total Income: $" + totalIncome + "\n" +
                     "Total Expense: $" + totalExpense + "\n" +
                     "Balance: $" + (totalIncome - totalExpense);

    System.out.println(summary); 
    return summary;
}
    
}

class Transaction {
    private Date date;
    private String description;
    private double amount;

    public Transaction(String description, double amount) {
        this.date = new Date();
        this.description = description;
        this.amount = amount;
    }

    public String getFormattedTransaction() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "Date: " + dateFormat.format(date) + "\nDescription: " + description + "\nAmount: $" + amount;
    }
}

class TransactionHistoryManager {
    private ArrayList<Transaction> transactions;

    public TransactionHistoryManager() {
        this.transactions = new ArrayList<>();
    }

    public String recordTransaction(String description, double amount) {
        Transaction transaction = new Transaction(description, amount);
        transactions.add(transaction);
        return transaction.getFormattedTransaction();
    }

    public String getTransactionHistory() {
        StringBuilder history = new StringBuilder("Transaction History:\n");
        for (Transaction transaction : transactions) {
            history.append(transaction.getFormattedTransaction()).append("\n------------------------\n");
        }
        return history.toString();
    }
}

class BudgetManager {
    private Map<String, Double> budgets;

    public BudgetManager() {
        this.budgets = new HashMap<>();
    }

    public void setBudget(String category, double amount) {
        budgets.put(category, amount);
        System.out.println("Budget set successfully for " + category + ": $" + amount);
    }

    public void trackSpending(String category, double spentAmount) {
    if (budgets.containsKey(category)) {
        double budgetAmount = budgets.get(category);
        double remainingBudget = budgetAmount - spentAmount;

        budgets.put(category, remainingBudget);

        System.out.println("Remaining budget for " + category + ": $" + remainingBudget);
    } else {
        System.out.println("No budget set for " + category + ". Consider setting a budget.");
    }
}

    public double getBudget(String category) {
        return budgets.getOrDefault(category, 0.0);
    }
}


class SavingsManager {
    private Map<String, Double> savings;

    public SavingsManager() {
        this.savings = new HashMap<>();
    }

    public void recordSavings(String accountName, double amount, double interestRate, String maturityDate) {
        savings.put(accountName, amount);
        System.out.println("Savings recorded successfully: " + accountName +
                " - Amount: $" + amount + ", Interest Rate: " + interestRate + "%, Maturity Date: " + maturityDate);
    }

    public String displaySavings() {
    StringBuilder savingsDetails = new StringBuilder("Savings and Investments:\n");
    for (Map.Entry<String, Double> entry : savings.entrySet()) {
        savingsDetails.append(entry.getKey()).append(" - Amount: $").append(entry.getValue()).append("\n");
    }
    return savingsDetails.toString();
}
    
}

class Goal {
    private String description;
    private double targetAmount;
    private double currentAmount;

    public Goal(String description, double targetAmount) {
        this.description = description;
        this.targetAmount = targetAmount;
        this.currentAmount = 0.0;
    }

    public void recordProgress(double amount) {
        currentAmount += amount;
        System.out.println("Progress recorded towards " + description + ": $" + amount);
    }

    public boolean isGoalAchieved() {
        return currentAmount >= targetAmount;
    }
    public double getCurrentAmount() {
        return currentAmount;
    }

    public double getTargetAmount() {
        return Math.max(0, targetAmount - currentAmount);
    }

    public void displayGoalDetails() {
        System.out.println("Goal: " + description);
        System.out.println("Target Amount: $" + targetAmount);
        System.out.println("Current Amount: $" + currentAmount);
    }
}

class GoalManager {
    private Map<String, Goal> goals;


    public GoalManager() {
        this.goals = new HashMap<>();
    }

    public void setGoal(String goalName, String description, double targetAmount) {
        Goal goal = new Goal(description, targetAmount);
        goals.put(goalName, goal);
        System.out.println("Financial goal set successfully: " + goalName);
    }

    public double recordProgress(String goalName, double amount) {
    if (goals.containsKey(goalName)) {
        Goal goal = goals.get(goalName);
        goal.recordProgress(amount);

        if (goal.isGoalAchieved()) {
            System.out.println("Congratulations! You've achieved your financial goal: " + goalName);
            return 0; 
        }

        double remainingAmount = goal.getTargetAmount();  
        return remainingAmount;
    } else {
        System.out.println("No goal set with the name: " + goalName);
        return -1; 
    }
}
}

class ExpenseAnalytics {
    private ArrayList<Double> expenses;
    private Map<String, Double> categoryExpenses;

    public ExpenseAnalytics() {
        this.expenses = new ArrayList<>();
        this.categoryExpenses = new HashMap<>();
    }


    public void recordExpense(double amount, String category) {
        expenses.add(amount);

        if (categoryExpenses.containsKey(category)) {
            double currentCategoryExpense = categoryExpenses.get(category);
            categoryExpenses.put(category, currentCategoryExpense + amount);
        } else {
            categoryExpenses.put(category, amount);
        }
    }

    public String viewExpenseAnalytics() {
        StringBuilder analyticsMessage = new StringBuilder("Expense Analytics:\n");
        analyticsMessage.append("Total Expenses: $").append(expenses.stream().mapToDouble(Double::doubleValue).sum()).append("\n");

        analyticsMessage.append("Category-wise Expenses:\n");
        for (Map.Entry<String, Double> entry : categoryExpenses.entrySet()) {
            analyticsMessage.append(entry.getKey()).append(": $").append(entry.getValue()).append("\n");
        }

        return analyticsMessage.toString();
    }
}

public class App extends Application {
    private UserAuthentication userAuth;
    private FinancialManager financialManager;
    private TransactionHistoryManager transactionHistoryManager;
    private BudgetManager budgetManager;
    private TextField usernameField;
    private PasswordField passwordField;
    private GridPane grid;
    private SavingsManager savingsManager;
    private String savingsDetails;
    private GoalManager goalManager;
    private ExpenseAnalytics expenseAnalytics;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        userAuth = new UserAuthentication("Kshitij", "pass123");
        financialManager = new FinancialManager();
        transactionHistoryManager = new TransactionHistoryManager();
        budgetManager = new BudgetManager();
        savingsManager = new SavingsManager();
        goalManager = new GoalManager();
        expenseAnalytics = new ExpenseAnalytics();
        primaryStage.setTitle("Personal Money Manager");
    
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
    
        addAuthenticationControls(grid, 0, 0);
        grid.setStyle("-fx-background-image: url('green2.jpg'); -fx-background-size: cover;");
        Scene scene = new Scene(grid, 1000, 700); 
        scene.getStylesheets().add("styles.css");
        primaryStage.setScene(scene);
    
        primaryStage.show();
    }

    private void addAuthenticationControls(GridPane grid, int col, int row) {
        Label authLabel = new Label("User Authentication");
        grid.add(authLabel, col, row, 2, 1);

        Label usernameLabel = new Label("Username:");
        grid.add(usernameLabel, col, row + 1);

        usernameField = new TextField();
        grid.add(usernameField, col + 1, row + 1);

        Label passwordLabel = new Label("Password:");
        grid.add(passwordLabel, col, row + 2);

        passwordField = new PasswordField();
        grid.add(passwordField, col + 1, row + 2);

        Button authButton = new Button("Authenticate");
        grid.add(authButton, col + 1, row + 3);

        authLabel.getStyleClass().add("title-label");
        authButton.getStyleClass().add("auth-button");

        authButton.setOnAction(e -> {
            String enteredUsername = usernameField.getText();
            String enteredPassword = passwordField.getText();

            if (userAuth.authenticateUser(enteredUsername, enteredPassword)) {
                addFinancialControls(grid, 0, 7);
                addTransactionHistoryControls(grid, 2, 0);
                addBudgetManagerControls(grid, 2, 7);
                addSavingsManagerControls(grid, 4, 0);
                addGoalManagerControls(grid, 4, 7);
                addExpenseAnalyticsControls(grid, 0, 0);

                showAlert("Authentication Successful", "Access granted.");

                grid.getChildren().removeAll(authLabel, usernameLabel, usernameField, passwordLabel, passwordField, authButton);
            } else {
                showAlert("Authentication Failed", "Access denied.");
            }
        });
    }

    private void addFinancialControls(GridPane grid, int col, int row) {
        Label financialLabel = new Label("Financial Management");
        grid.add(financialLabel, col, row, 2, 1);

        Label incomeLabel = new Label("Income:");
        grid.add(incomeLabel, col, row + 1);

        TextField incomeField = new TextField();
        grid.add(incomeField, col + 1, row + 1);

        Label expenseLabel = new Label("Expense:");
        grid.add(expenseLabel, col, row + 2);

        TextField expenseField = new TextField();
        grid.add(expenseField, col + 1, row + 2);

        Button recordButton = new Button("Record Transaction");
        grid.add(recordButton, col + 1, row + 3);
        Button showSummaryButton = new Button("Show Summary");
        grid.add(showSummaryButton, col + 1, row + 4);

        financialLabel.getStyleClass().add("title-label");
        recordButton.getStyleClass().add("record-button");
        showSummaryButton.getStyleClass().add("summary-button");


        recordButton.setOnAction(e -> {
            try {
                double incomeAmount = Double.parseDouble(incomeField.getText());
                double expenseAmount = Double.parseDouble(expenseField.getText());
        
                String incomeMessage = financialManager.recordIncome(incomeAmount);
                String expenseMessage = financialManager.recordExpense(expenseAmount);
        
                showAlert("Transaction Recorded", incomeMessage + "\n" + expenseMessage);
                financialManager.displaySummary();
            } catch (NumberFormatException ex) {
                showAlert("Invalid Input", "Please enter valid numeric values for income and expense.");
            }
        });
        showSummaryButton.setOnAction(e -> {
            String summaryMessage = financialManager.displaySummary();
            showAlert("Financial Summary", summaryMessage);
        });
    }

    private void addTransactionHistoryControls(GridPane grid, int col, int row) {
        Label historyLabel = new Label("Transaction History");
        grid.add(historyLabel, col, row, 2, 1);

        Label descriptionLabel = new Label("Description:");
        grid.add(descriptionLabel, col, row + 1);

        TextField descriptionField = new TextField();
        grid.add(descriptionField, col + 1, row + 1);

        Label amountLabel = new Label("Amount:");
        grid.add(amountLabel, col, row + 2);

        TextField amountField = new TextField();
        grid.add(amountField, col + 1, row + 2);
        Button recordTransactionButton = new Button("Record Transaction");
        grid.add(recordTransactionButton, col + 1, row + 3);

        Button displayHistoryButton = new Button("Display Transaction History");
        grid.add(displayHistoryButton, col + 1, row + 4);

        historyLabel.getStyleClass().add("title-label");
        recordTransactionButton.getStyleClass().add("record-button");
        displayHistoryButton.getStyleClass().add("history-button");

        recordTransactionButton.setOnAction(e -> {
            try {
                String description = descriptionField.getText();
                double amount = Double.parseDouble(amountField.getText());

                transactionHistoryManager.recordTransaction(description, amount);

                showAlert("Transaction Recorded", "Description: " + description + "\nAmount: $" + amount);

            } catch (NumberFormatException ex) {
                showAlert("Invalid Input", "Please enter a valid numeric value for amount.");
            }
        });

        displayHistoryButton.setOnAction(e -> {
            showAlert("Transaction History", transactionHistoryManager.getTransactionHistory());
        });
        
    }

    private void addBudgetManagerControls(GridPane grid, int col, int row) {
        Label budgetLabel = new Label("Budget Management");
        grid.add(budgetLabel, col, row, 2, 1);

        Label categoryLabel = new Label("Category:");
        grid.add(categoryLabel, col, row + 1);

        TextField categoryField = new TextField();
        grid.add(categoryField, col + 1, row + 1);

        Label amountLabel = new Label("Amount:");
        grid.add(amountLabel, col, row + 2);

        TextField amountField = new TextField();
        grid.add(amountField, col + 1, row + 2);

        Button setBudgetButton = new Button("Set Budget");
        grid.add(setBudgetButton, col + 1, row + 3);

        Button trackSpendingButton = new Button("Track Spending");
        grid.add(trackSpendingButton, col + 1, row + 4);

        budgetLabel.getStyleClass().add("title-label");
        categoryLabel.getStyleClass().add("input-label");
        amountLabel.getStyleClass().add("input-label");
        setBudgetButton.getStyleClass().add("record-button");
        trackSpendingButton.getStyleClass().add("track-button");

        setBudgetButton.setOnAction(e -> {
            try {
                String category = categoryField.getText();
                double amount = Double.parseDouble(amountField.getText());

                budgetManager.setBudget(category, amount);
                showAlert("Budget Set", "Budget set successfully for " + category + ": $" + amount);
            } catch (NumberFormatException ex) {
                showAlert("Invalid Input", "Please enter a valid numeric value for amount.");
            }
        });

        trackSpendingButton.setOnAction(e -> {
            try {
                String category = categoryField.getText();
                double spentAmount = Double.parseDouble(amountField.getText());
        
                budgetManager.trackSpending(category, spentAmount);
        
                double remainingBudget = budgetManager.getBudget(category);
        
                showAlert("Spending Tracked", "Remaining budget for " + category + ": $" + remainingBudget);
        
            } catch (NumberFormatException ex) {
                showAlert("Invalid Input", "Please enter a valid numeric value for spending amount.");
            }
        });
    }

    private void addSavingsManagerControls(GridPane grid, int col, int row) {
        Label savingsLabel = new Label("Savings and Investments");
        grid.add(savingsLabel, col, row, 2, 1);
    
        Label accountNameLabel = new Label("Account Name:");
        grid.add(accountNameLabel, col, row + 1);
    
        TextField accountNameField = new TextField();
        grid.add(accountNameField, col + 1, row + 1);
    
        Label amountLabel = new Label("Amount:");
        grid.add(amountLabel, col, row + 2);
    
        TextField amountField = new TextField();
        grid.add(amountField, col + 1, row + 2);
    
        Label interestRateLabel = new Label("Interest Rate:");
        grid.add(interestRateLabel, col, row + 3);
    
        TextField interestRateField = new TextField();
        grid.add(interestRateField, col + 1, row + 3);
    
        Label maturityDateLabel = new Label("Maturity Date:");
        grid.add(maturityDateLabel, col, row + 4);
    
        TextField maturityDateField = new TextField();
        grid.add(maturityDateField, col + 1, row + 4);
    
        Button recordSavingsButton = new Button("Record Savings");
        grid.add(recordSavingsButton, col + 1, row + 5);
    
        Button displaySavingsButton = new Button("Display Savings");
        grid.add(displaySavingsButton, col + 1, row + 6);

        savingsLabel.getStyleClass().add("title-label");
        accountNameLabel.getStyleClass().add("input-label");
        amountLabel.getStyleClass().add("input-label");
        interestRateLabel.getStyleClass().add("input-label");
        maturityDateLabel.getStyleClass().add("input-label");
        recordSavingsButton.getStyleClass().add("record-button");
        displaySavingsButton.getStyleClass().add("view-button");
    
        recordSavingsButton.setOnAction(e -> {
            try {
                String accountName = accountNameField.getText();
                double amount = Double.parseDouble(amountField.getText());
                double interestRate = Double.parseDouble(interestRateField.getText());
                String maturityDate = maturityDateField.getText();
    
                savingsManager.recordSavings(accountName, amount, interestRate, maturityDate);
                showAlert("Savings Recorded", "Savings recorded successfully for " + accountName);
    
            } catch (NumberFormatException ex) {
                showAlert("Invalid Input", "Please enter valid numeric values.");
            }
        });
    
        displaySavingsButton.setOnAction(e -> {
            savingsDetails = savingsManager.displaySavings();
            showAlert("Savings Details", savingsDetails);
        });
    }

    private void addGoalManagerControls(GridPane grid, int col, int row) {
        Label goalLabel = new Label("Financial Goals");
        grid.add(goalLabel, col, row, 2, 1);
    
        Label goalNameLabel = new Label("Goal Name:");
        grid.add(goalNameLabel, col, row + 1);
    
        TextField goalNameField = new TextField();
        grid.add(goalNameField, col + 1, row + 1);
    
        Label goalAmountLabel = new Label("Goal Amount:");
        grid.add(goalAmountLabel, col, row + 2);
    
        TextField goalAmountField = new TextField();
        grid.add(goalAmountField, col + 1, row + 2);
    
        Button setGoalButton = new Button("Set Financial Goal");
        grid.add(setGoalButton, col + 1, row + 3);
    
        Button recordProgressButton = new Button("Record Progress");
        grid.add(recordProgressButton, col + 1, row + 4);

        setGoalButton.setOnAction(e -> {
            try {
                String goalName = goalNameField.getText();
                double goalAmount = Double.parseDouble(goalAmountField.getText());
    
                goalManager.setGoal(goalName, "Description", goalAmount);
                showAlert("Goal Set", "Financial goal set successfully: " + goalName);
            } catch (NumberFormatException ex) {
                showAlert("Invalid Input", "Please enter a valid numeric value for goal amount.");
            }
        });
        goalLabel.getStyleClass().add("title-label");
        goalNameLabel.getStyleClass().add("input-label");
        goalAmountLabel.getStyleClass().add("input-label");
        setGoalButton.getStyleClass().add("record-button");
        recordProgressButton.getStyleClass().add("record-button");
    
        recordProgressButton.setOnAction(e -> {
            try {
                String goalName = goalNameField.getText();
                double progressAmount = Double.parseDouble(goalAmountField.getText());
        
                double remainingAmount = goalManager.recordProgress(goalName, progressAmount);
                if (remainingAmount >= 0) {
                    if (remainingAmount == 0) {
                        showAlert("Goal Achieved", "Congratulations! You've achieved your financial goal: " + goalName);
                    } else {
                        showAlert("Progress Recorded", "Progress recorded towards " + goalName +
                                ": $" + progressAmount + "\nRemaining Amount: $" + remainingAmount);
                    }
                } else {
                    showAlert("Invalid Goal", "No goal set with the name: " + goalName);
                }
            } catch (NumberFormatException ex) {
                showAlert("Invalid Input", "Please enter a valid numeric value for progress amount.");
            }
        });
    }
    private void addExpenseAnalyticsControls(GridPane grid, int col, int row) {
        Label analyticsLabel = new Label("Expense Analytics");
    grid.add(analyticsLabel, col, row, 2, 1);
    analyticsLabel.getStyleClass().add("analytics-label");
    
        Label amountLabel = new Label("Amount:");
        grid.add(amountLabel, col, row + 1);
    
        TextField amountField = new TextField();
        grid.add(amountField, col + 1, row + 1);
    
        Label categoryLabel = new Label("Category:");
        grid.add(categoryLabel, col, row + 2);
    
        TextField categoryField = new TextField();
        grid.add(categoryField, col + 1, row + 2);
    
        Button recordExpenseButton = new Button("Record Expense");
    grid.add(recordExpenseButton, col + 1, row + 3);
    recordExpenseButton.getStyleClass().add("record-expense-button");
    
        Button viewAnalyticsButton = new Button("View Analytics");
    grid.add(viewAnalyticsButton, col + 1, row + 4);
    viewAnalyticsButton.getStyleClass().add("view-button");
    
        recordExpenseButton.setOnAction(e -> {
            try {
                double expenseAmount = Double.parseDouble(amountField.getText());
                String expenseCategory = categoryField.getText();
    
                expenseAnalytics.recordExpense(expenseAmount, expenseCategory);
    
                showAlert("Expense Recorded", "Expense recorded successfully: $" + expenseAmount + " in category " + expenseCategory);
            } catch (NumberFormatException ex) {
                showAlert("Invalid Input", "Please enter a valid numeric value for the expense amount.");
            }
        });
    
        viewAnalyticsButton.setOnAction(e -> {
            String analyticsMessage = expenseAnalytics.viewExpenseAnalytics();
            showAlert("Expense Analytics", analyticsMessage);
        });
    }
    

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
    
        if (message != null && !message.isEmpty()) {
            alert.setContentText(message);
        } else {
            alert.setContentText("No message to display.");
        }
    
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
    
        alert.showAndWait();
    }
}