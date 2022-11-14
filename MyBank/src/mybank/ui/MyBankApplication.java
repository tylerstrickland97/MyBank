package mybank.ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import mybank.data.Account;
import mybank.data.Transaction;
import mybank.manager.BankManager;
import mybank.user.Depositor;
import mybank.user.Employee;

public class MyBankApplication implements ActionListener {

    private static BankManager         manager;

    static JFrame                      loginFrame;

    static JFrame                      employeeFrame;

    private JFrame                     depositorFrame;

    private static JButton             employeeSaveButton;
    private static JButton             addEmployeeButton;
    private static JButton             editEmployeeButton;
    private static JButton             logoutButton;
    private static JButton             addDepositorButton;
    private static JButton             removeDepositorButton;
    private static JButton             loginButton;
    private JButton                    addAccountButton;
    private JButton                    removeAccountButton;
    private JButton                    addTransactionButton;
    private JButton                    makeWithdrawalButton;
    private JButton                    makeDepositButton;
    private JButton                    editAccountNameButton;
    private JButton                    editInformationButton;
    private static JPasswordField      passwordField;
    private static JTextField          usernameField;
    private static JTable              tableEmployees;
    private static JButton             employeeFormAddButton;
    private static DepositorTableModel empTableModel;
    private static JPanel              empPanel;
    private static JPanel              mainEmployeePanel;
    private File                       selected;
    private JComboBox<String>          accountSelect;
    private JLabel                     accountName;
    private JLabel                     accountReference;
    private JLabel                     accountBalance;
    private String                     selectedAccountName;
    private TransactionTableModel      transactionTableModel;
    private JTable                     transactionTable;

    public void makeLoginGUI () {
        loginFrame = new JFrame();
        loginFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        loginFrame.setSize( 700, 700 );
        JFileChooser j = new JFileChooser();
        j.setCurrentDirectory( new File( "./" ) );
        int option = j.showOpenDialog( loginFrame );
        selected = null;
        if ( option == JFileChooser.APPROVE_OPTION ) {
            selected = j.getSelectedFile();
        }
        manager.loadEmployees( selected.getAbsolutePath() );
        j.setVisible( false );
        loginFrame.setTitle( "Login" );

        JPanel panel = new JPanel();
        panel.setLayout( null );
        loginFrame.add( panel );
        JLabel usernameLabel = new JLabel( "Username:" );
        JLabel passwordLabel = new JLabel( "Password:" );
        usernameLabel.setBounds( 200, 250, 100, 30 );
        passwordLabel.setBounds( 200, 290, 100, 30 );
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        usernameField.setBounds( 300, 250, 150, 30 );
        passwordField.setBounds( 300, 290, 150, 30 );
        loginButton = new JButton( "Login" );
        loginButton.setBounds( 190, 350, 265, 30 );
        loginButton.setOpaque( true );
        loginButton.addActionListener( this );
        panel.add( usernameLabel );
        panel.add( passwordLabel );
        panel.add( usernameField );
        panel.add( passwordField );
        panel.add( loginButton );
        loginFrame.setVisible( true );
    }

    @Override
    public void actionPerformed ( ActionEvent e ) {
        if ( e.getSource() == loginButton ) {
            try {
                String password = String.valueOf( passwordField.getPassword() );
                if ( !manager.login( usernameField.getText(), password ) ) {
                    JOptionPane.showMessageDialog( loginFrame, "Incorrect username or password" );
                }
                else {
                    if ( manager.getCurrentUser() instanceof Employee ) {
                        loginFrame.dispose();
                        makeEmployeeGUI();
                    }
                    else {
                        Depositor d = (Depositor) manager.getCurrentUser();
                        if ( d.getAccounts().get( 0 ) != null ) {
                            selectedAccountName = d.getAccounts().get( 0 ).getName();
                        }
                        else {
                            selectedAccountName = null;
                        }
                        makeDepositorGUI();
                    }

                }

            }
            catch ( IllegalArgumentException ex ) {
                JOptionPane.showMessageDialog( loginFrame, ex.getMessage() );
            }
        }
        else if ( e.getSource() == employeeSaveButton ) {
            JFrame saveFrame = new JFrame();
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory( new File( "./" ) );
            int userSelection = fileChooser.showSaveDialog( saveFrame );

            if ( userSelection == JFileChooser.APPROVE_OPTION ) {
                File fileToSave = fileChooser.getSelectedFile();
                manager.save( fileToSave.getAbsolutePath() );
            }
            saveFrame.dispose();

        }
        else if ( e.getSource() == addEmployeeButton ) {
            addEmployeeForm();
        }
        else if ( e.getSource() == removeDepositorButton ) {

            int row = tableEmployees.getSelectedRow();
            if ( row == -1 ) {
                JOptionPane.showMessageDialog( employeeFrame, "No Depositor selected" );
                return;
            }
            String id = (String) tableEmployees.getValueAt( row, 0 );
            try {
                manager.removeDepositor( id );
                empTableModel.updateData();
                JOptionPane.showMessageDialog( employeeFrame, "Successfully removed Depositor" );
                return;
            }
            catch ( IllegalArgumentException ex ) {
                JOptionPane.showMessageDialog( employeeFrame, ex.getMessage() );
                return;
            }

        }
        else if ( e.getSource() == editEmployeeButton ) {
            editEmployeeForm();
        }
        else if ( e.getSource() == logoutButton ) {
            manager.logout();
            employeeFrame.dispose();
            makeLoginGUI();
        }
        else if ( e.getSource() == addDepositorButton ) {
            addDepositorForm();
        }
        else if ( e.getSource() == makeWithdrawalButton ) {
            makeWithdrawalForm();
        }
        else if ( e.getSource() == makeDepositButton ) {
            makeDepositForm();
        }
        else if ( e.getSource() == addTransactionButton ) {
            makeTransactionForm();
        }
        else if ( e.getSource() == removeAccountButton ) {
            Account a = manager.getAccountByName( selectedAccountName );
            try {
                manager.removeAccount( a );
                accountSelect.removeItem( selectedAccountName );
                transactionTableModel.updateData();
                transactionTableModel.fireTableDataChanged();
                a = manager.getAccountByName( selectedAccountName );
                if ( a != null ) {
                    accountName.setText( "Account Name: " + a.getName() );
                    accountBalance.setText( "Account Balance: " + a.getBalance() );
                    accountReference.setText( "Account Reference: " + a.getReference() );
                }
                else {
                    accountName.setText( "Account Name: " );
                    accountBalance.setText( "Account Balance: " );
                    accountReference.setText( "Account Reference: " );
                }
            }
            catch ( Exception ex ) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog( depositorFrame, ex.getMessage() );
            }

        }
        else if ( e.getSource() == editAccountNameButton ) {
            makeEditAccountNameForm();
        }
        else if ( e.getSource() == addAccountButton ) {
            makeNewAccountForm();
        }
        else if ( e.getSource() == editInformationButton ) {
            editDepositorForm();
        }

    }

    public void editDepositorForm () {
        JFrame editDepositorFrame = new JFrame( "Edit Personal Information" );
        editDepositorFrame.setSize( new Dimension( 750, 750 ) );
        editDepositorFrame.setResizable( false );

        Depositor d = (Depositor) manager.getCurrentUser();

        JPanel main = new JPanel();
        main.setLayout( null );
        JLabel firstLabel = new JLabel( "First" );
        firstLabel.setBounds( 200, 200, 150, 30 );
        main.add( firstLabel );

        JLabel lastLabel = new JLabel( "Last" );
        lastLabel.setBounds( 200, 250, 150, 30 );
        main.add( lastLabel );

        JLabel passwordLabel = new JLabel( "Password" );
        passwordLabel.setBounds( 200, 300, 150, 30 );
        main.add( passwordLabel );

        JLabel emailLabel = new JLabel( "Email" );
        emailLabel.setBounds( 200, 350, 320, 30 );
        main.add( emailLabel );

        JLabel birthdayLabel = new JLabel( "Birthday" );
        birthdayLabel.setBounds( 200, 400, 320, 30 );
        main.add( birthdayLabel );

        JTextField firstField = new JTextField();
        firstField.setBounds( 300, 200, 250, 30 );
        firstField.setText( d.getFirst() );
        main.add( firstField );

        JTextField lastField = new JTextField();
        lastField.setBounds( 300, 250, 250, 30 );
        lastField.setText( d.getLast() );
        main.add( lastField );

        JTextField passwordField = new JPasswordField();
        passwordField.setBounds( 300, 300, 250, 30 );
        passwordField.setText( d.getPassword() );
        main.add( passwordField );

        JTextField emailField = new JTextField();
        emailField.setBounds( 300, 350, 250, 30 );
        emailField.setText( d.getEmail() );
        main.add( emailField );

        JTextField birthdayField = new JTextField();
        birthdayField.setBounds( 300, 400, 250, 30 );
        birthdayField.setText( d.getBirthday() );
        main.add( birthdayField );

        JButton editDepositorButton = new JButton( "Edit Information" );
        editDepositorButton.setBounds( 190, 500, 265, 30 );
        editDepositorButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed ( ActionEvent event ) {
                String firstName = firstField.getText();
                String lastName = lastField.getText();
                String email = emailField.getText();
                String password = passwordField.getText();
                String birthday = birthdayField.getText();
                try {
                    manager.editDepositorAccount( firstName, lastName, email, password, birthday );
                    JOptionPane.showMessageDialog( main, "Successfully edited information" );
                }
                catch ( IllegalArgumentException e ) {
                    JOptionPane.showMessageDialog( main, e.getMessage() );
                }
            }
        } );

        main.add( editDepositorButton );
        editDepositorFrame.add( main );
        editDepositorFrame.setVisible( true );

    }

    public void makeNewAccountForm () {
        JFrame newAccountFrame = new JFrame( "Add a New Account" );
        newAccountFrame.setSize( new Dimension( 750, 500 ) );
        newAccountFrame.setResizable( false );

        JLabel accountNameLabel = new JLabel( "Account Name" );
        JTextField accountNameField = new JTextField();
        accountNameLabel.setBounds( 250, 200, 100, 20 );
        accountNameField.setBounds( 350, 200, 150, 20 );

        JCheckBox checkingCheckbox = new JCheckBox( "Checking" );
        JCheckBox savingsCheckBox = new JCheckBox( "Savings" );
        checkingCheckbox.setBounds( 250, 250, 100, 20 );
        savingsCheckBox.setBounds( 375, 250, 100, 20 );

        JButton createAccountButton = new JButton( "Create New Account" );
        createAccountButton.setBounds( 250, 300, 200, 30 );
        createAccountButton.addActionListener( new ActionListener() {

            @Override
            public void actionPerformed ( ActionEvent e ) {
                String name = accountNameField.getText();
                boolean checking = checkingCheckbox.isSelected();
                boolean savings = savingsCheckBox.isSelected();
                try {
                    manager.addNewAccount( name, checking, savings );
                    accountSelect.addItem( name );
                    JOptionPane.showMessageDialog( newAccountFrame, "Success" );
                }
                catch ( Exception ex ) {
                    JOptionPane.showMessageDialog( newAccountFrame, ex.getMessage() );
                }
            }

        } );

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout( null );

        mainPanel.add( accountNameLabel );
        mainPanel.add( accountNameField );
        mainPanel.add( checkingCheckbox );
        mainPanel.add( savingsCheckBox );
        mainPanel.add( createAccountButton );

        newAccountFrame.add( mainPanel );
        newAccountFrame.setVisible( true );

    }

    public void makeEditAccountNameForm () {
        JFrame editNameFrame = new JFrame( "Edit Account Name" );
        editNameFrame.setSize( new Dimension( 700, 400 ) );
        editNameFrame.setResizable( false );
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout( null );

        JLabel accountNameLabel = new JLabel( "Account Name" );
        accountNameLabel.setBounds( 225, 125, 100, 20 );
        JTextField accountNameField = new JTextField();
        accountNameField.setBounds( 325, 125, 150, 20 );

        JButton btn = new JButton( "Edit Account Name" );
        btn.setBounds( 225, 250, 200, 20 );
        btn.addActionListener( new ActionListener() {

            @Override
            public void actionPerformed ( ActionEvent e ) {
                String newName = accountNameField.getText();

                Account a = manager.getAccountByName( selectedAccountName );
                try {
                    accountSelect.removeItem( selectedAccountName );
                    accountSelect.addItem( newName );
                    a.setName( newName );
                    selectedAccountName = newName;
                    accountName.setText( "Account Name: " + newName );
                    JOptionPane.showMessageDialog( editNameFrame, "Success" );
                }
                catch ( Exception ex ) {
                    JOptionPane.showMessageDialog( editNameFrame, ex.getMessage() );
                }

            }

        } );

        mainPanel.add( accountNameLabel );
        mainPanel.add( accountNameField );
        mainPanel.add( btn );
        editNameFrame.add( mainPanel );
        editNameFrame.setVisible( true );

    }

    public void addDepositorForm () {
        JFrame addFrame = new JFrame( "Add new Depositor" );
        addFrame.setSize( 750, 750 );

        JPanel main = new JPanel();
        main.setLayout( null );
        JLabel firstLabel = new JLabel( "First" );
        firstLabel.setBounds( 200, 200, 150, 30 );
        main.add( firstLabel );

        JLabel lastLabel = new JLabel( "Last" );
        lastLabel.setBounds( 200, 250, 150, 30 );
        main.add( lastLabel );

        JLabel emailLabel = new JLabel( "Email" );
        emailLabel.setBounds( 200, 300, 150, 30 );
        main.add( emailLabel );

        JLabel passwordLabel = new JLabel( "Password" );
        passwordLabel.setBounds( 200, 350, 150, 30 );
        main.add( passwordLabel );

        JLabel idLabel = new JLabel( "ID" );
        idLabel.setBounds( 200, 400, 320, 30 );
        main.add( idLabel );

        JLabel birthdayLabel = new JLabel( "Birthday" );
        birthdayLabel.setBounds( 200, 450, 150, 30 );
        main.add( birthdayLabel );

        JTextField firstField = new JTextField();
        firstField.setBounds( 300, 200, 150, 30 );
        main.add( firstField );

        JTextField lastField = new JTextField();
        lastField.setBounds( 300, 250, 150, 30 );
        main.add( lastField );

        JTextField emailField = new JTextField();
        emailField.setBounds( 300, 300, 150, 30 );
        main.add( emailField );

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds( 300, 350, 150, 30 );
        main.add( passwordField );

        JTextField idField = new JTextField();
        idField.setBounds( 300, 400, 150, 30 );
        main.add( idField );

        JTextField birthdayField = new JTextField();
        birthdayField.setBounds( 300, 450, 150, 30 );
        main.add( birthdayField );

        JButton depositorFormAddButton = new JButton( "Add Depositor" );
        depositorFormAddButton.setBounds( 190, 500, 265, 30 );
        depositorFormAddButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed ( ActionEvent event ) {
                String firstName = firstField.getText();
                String lastName = lastField.getText();
                String email = emailField.getText();
                String id = idField.getText();
                String password = String.valueOf( passwordField.getPassword() );
                String birthday = birthdayField.getText();
                try {
                    manager.addNewDepositor( firstName, lastName, email, password, id, birthday );
                    empTableModel.updateData();
                    JOptionPane.showMessageDialog( main, "Successfully added new Depositor" );
                }
                catch ( IllegalArgumentException e ) {
                    JOptionPane.showMessageDialog( main, e.getMessage() );
                }
            }
        } );
        main.add( depositorFormAddButton );

        addFrame.add( main );
        addFrame.setVisible( true );
    }

    public void addEmployeeForm () {
        JFrame addFrame = new JFrame( "Add new Employee" );
        addFrame.setSize( 750, 750 );

        JPanel main = new JPanel();
        main.setLayout( null );
        JLabel firstLabel = new JLabel( "First" );
        firstLabel.setBounds( 200, 200, 150, 30 );
        main.add( firstLabel );

        JLabel lastLabel = new JLabel( "Last" );
        lastLabel.setBounds( 200, 250, 150, 30 );
        main.add( lastLabel );

        JLabel passwordLabel = new JLabel( "Password" );
        passwordLabel.setBounds( 200, 300, 150, 30 );
        main.add( passwordLabel );

        JLabel idLabel = new JLabel( "ID" );
        idLabel.setBounds( 200, 350, 320, 30 );
        main.add( idLabel );

        JLabel birthdayLabel = new JLabel( "Birthday" );
        birthdayLabel.setBounds( 200, 400, 150, 30 );
        main.add( birthdayLabel );

        JTextField firstField = new JTextField();
        firstField.setBounds( 300, 200, 150, 30 );
        main.add( firstField );

        JTextField lastField = new JTextField();
        lastField.setBounds( 300, 250, 150, 30 );
        main.add( lastField );

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds( 300, 300, 150, 30 );
        main.add( passwordField );

        JTextField idField = new JTextField();
        idField.setBounds( 300, 350, 150, 30 );
        main.add( idField );

        JTextField birthdayField = new JTextField();
        birthdayField.setBounds( 300, 400, 150, 30 );
        main.add( birthdayField );

        employeeFormAddButton = new JButton( "Add Employee" );
        employeeFormAddButton.setBounds( 190, 500, 265, 30 );
        employeeFormAddButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed ( ActionEvent event ) {
                String firstName = firstField.getText();
                String lastName = lastField.getText();
                String id = idField.getText();
                String password = String.valueOf( passwordField.getPassword() );
                String birthday = birthdayField.getText();
                try {
                    manager.addNewEmployee( firstName, lastName, password, id, birthday );
                    JOptionPane.showMessageDialog( main, "Successfully added new Employee" );
                }
                catch ( IllegalArgumentException e ) {
                    JOptionPane.showMessageDialog( main, e.getMessage() );
                }
            }
        } );
        main.add( employeeFormAddButton );

        addFrame.add( main );
        addFrame.setVisible( true );
    }

    public void makeWithdrawalForm () {
        JFrame withdrawalFrame = new JFrame( "Make a Withdrawal" );
        withdrawalFrame.setSize( 500, 400 );
        withdrawalFrame.setResizable( false );
        JPanel main = new JPanel();
        main.setLayout( null );
        JLabel amountLabel = new JLabel( "Amount" );
        JTextField amountField = new JTextField();

        amountLabel.setBounds( 125, 125, 150, 20 );
        amountField.setBounds( 200, 125, 100, 20 );

        JButton submit = new JButton( "Make Withdrawal" );
        submit.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed ( ActionEvent event ) {
                String amountString = amountField.getText();
                double amount = Double.parseDouble( amountString );
                Account a = manager.getAccountByName( selectedAccountName );
                try {
                    manager.makeWithdrawal( amount, a );
                    transactionTableModel.updateData();
                    transactionTableModel.fireTableDataChanged();
                    accountBalance.setText( "Account Balance: " + a.getBalance() );
                    withdrawalFrame.dispose();
                }
                catch ( Exception e ) {
                    JOptionPane.showMessageDialog( withdrawalFrame, e.getMessage() );
                }
            }

        } );
        submit.setBounds( 125, 200, 200, 20 );

        main.add( amountLabel );
        main.add( amountField );
        main.add( submit );
        withdrawalFrame.add( main );
        withdrawalFrame.setVisible( true );

    }

    public void makeDepositForm () {
        JFrame depositFrame = new JFrame( "Make a Deposit" );
        depositFrame.setSize( 500, 400 );
        depositFrame.setResizable( false );
        JPanel main = new JPanel();
        main.setLayout( null );
        JLabel amountLabel = new JLabel( "Amount" );
        JTextField amountField = new JTextField();

        amountLabel.setBounds( 125, 125, 150, 20 );
        amountField.setBounds( 200, 125, 100, 20 );

        JButton submit = new JButton( "Make Deposit" );
        submit.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed ( ActionEvent event ) {
                String amountString = amountField.getText();
                double amount = Double.parseDouble( amountString );
                Account a = manager.getAccountByName( selectedAccountName );
                try {
                    manager.makeDeposit( amount, a );
                    transactionTableModel.updateData();
                    transactionTableModel.fireTableDataChanged();
                    accountBalance.setText( "Account Balance: " + a.getBalance() );
                    depositFrame.dispose();
                }
                catch ( Exception e ) {
                    JOptionPane.showMessageDialog( depositFrame, e.getMessage() );
                }
            }

        } );
        submit.setBounds( 125, 200, 200, 20 );

        main.add( amountLabel );
        main.add( amountField );
        main.add( submit );
        depositFrame.add( main );
        depositFrame.setVisible( true );
    }

    public void makeTransactionForm () {
        JFrame depositFrame = new JFrame( "Make a Transaction" );
        depositFrame.setSize( 500, 400 );
        depositFrame.setResizable( false );
        JPanel main = new JPanel();
        main.setLayout( null );
        JLabel amountLabel = new JLabel( "Amount" );
        JTextField amountField = new JTextField();
        JLabel descriptionLabel = new JLabel( "Description" );
        JTextField descriptionField = new JTextField();

        amountLabel.setBounds( 125, 90, 150, 20 );
        amountField.setBounds( 200, 90, 100, 20 );
        descriptionLabel.setBounds( 125, 130, 150, 20 );
        descriptionField.setBounds( 200, 130, 150, 20 );

        JButton submit = new JButton( "Make Transaction" );
        submit.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed ( ActionEvent event ) {
                String amountString = amountField.getText();
                double amount = Double.parseDouble( amountString );
                String description = descriptionField.getText();
                Account a = manager.getAccountByName( selectedAccountName );
                try {
                    manager.addTransaction( description, amount, a );
                    transactionTableModel.updateData();
                    transactionTableModel.fireTableDataChanged();
                    accountBalance.setText( "Account Balance: " + a.getBalance() );
                    depositFrame.dispose();
                }
                catch ( Exception e ) {
                    JOptionPane.showMessageDialog( depositFrame, e.getMessage() );
                }
            }

        } );
        submit.setBounds( 125, 200, 200, 20 );

        main.add( amountLabel );
        main.add( amountField );
        main.add( descriptionLabel );
        main.add( descriptionField );
        main.add( submit );
        depositFrame.add( main );
        depositFrame.setVisible( true );
    }

    public void editEmployeeForm () {
        JFrame editFrame = new JFrame( "Edit Information" );
        editFrame.setSize( 750, 750 );

        Employee e = (Employee) manager.getCurrentUser();

        JPanel main = new JPanel();
        main.setLayout( null );
        JLabel firstLabel = new JLabel( "First" );
        firstLabel.setBounds( 200, 200, 150, 30 );
        main.add( firstLabel );

        JLabel lastLabel = new JLabel( "Last" );
        lastLabel.setBounds( 200, 250, 150, 30 );
        main.add( lastLabel );

        JLabel passwordLabel = new JLabel( "Password" );
        passwordLabel.setBounds( 200, 300, 150, 30 );
        main.add( passwordLabel );

        JLabel idLabel = new JLabel( "ID" );
        idLabel.setBounds( 200, 350, 320, 30 );
        main.add( idLabel );

        JLabel birthdayLabel = new JLabel( "Birthday" );
        birthdayLabel.setBounds( 200, 400, 150, 30 );
        main.add( birthdayLabel );

        JTextField firstField = new JTextField();
        firstField.setBounds( 300, 200, 150, 30 );
        firstField.setText( e.getFirst() );
        main.add( firstField );

        JTextField lastField = new JTextField();
        lastField.setBounds( 300, 250, 150, 30 );
        lastField.setText( e.getLast() );
        main.add( lastField );

        JTextField passwordField = new JPasswordField();
        passwordField.setBounds( 300, 300, 150, 30 );
        passwordField.setText( e.getPassword() );
        main.add( passwordField );

        JTextField idField = new JTextField();
        idField.setBounds( 300, 350, 150, 30 );
        idField.setText( e.getId() );
        main.add( idField );

        JTextField birthdayField = new JTextField();
        birthdayField.setBounds( 300, 400, 150, 30 );
        birthdayField.setText( e.getBirthday() );
        main.add( birthdayField );

        JButton employeeEditButton = new JButton( "Edit Employee" );
        employeeEditButton.setBounds( 190, 500, 265, 30 );
        employeeEditButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed ( ActionEvent event ) {
                String firstName = firstField.getText();
                String lastName = lastField.getText();
                String id = idField.getText();
                String password = passwordField.getText();
                String birthday = birthdayField.getText();
                try {
                    manager.editEmployeeAccount( firstName, lastName, password, id, birthday );
                    JOptionPane.showMessageDialog( main, "Successfully edited Employee" );
                }
                catch ( IllegalArgumentException e ) {
                    JOptionPane.showMessageDialog( main, e.getMessage() );
                }
            }
        } );
        main.add( employeeEditButton );

        editFrame.add( main );
        editFrame.setVisible( true );
    }

    public void makeDepositorGUI () {
        depositorFrame = new JFrame();
        depositorFrame.setSize( 750, 750 );
        depositorFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        JPanel mainDepositorPanel = new JPanel( new GridLayout( 3, 1 ) );
        JPanel accountPanel = new JPanel();
        accountPanel.setPreferredSize( new Dimension( 700, 150 ) );
        accountPanel.setLayout( null );

        Depositor d = (Depositor) manager.getCurrentUser();
        String[] accountNames = new String[d.getAccounts().size()];
        for ( int i = 0; i < d.getAccounts().size(); i++ ) {
            String accountName = d.getAccounts().get( i ).getName();
            accountNames[i] = accountName;
        }
        JLabel accountSelectLabel = new JLabel( "Choose Account" );
        accountSelect = new JComboBox<String>( accountNames );
        accountSelect.addItemListener( new ItemChangeListener() );
        accountName = new JLabel( "Account Name: " );
        accountName.setBounds( 400, 75, 300, 20 );
        accountReference = new JLabel( "Reference Number: " );
        accountReference.setBounds( 400, 100, 300, 20 );
        accountBalance = new JLabel( "Account Balance: " );
        accountBalance.setBounds( 400, 125, 3000, 20 );
        accountSelect.setBounds( 50, 100, 200, 40 );
        accountSelectLabel.setBounds( 50, 75, 200, 25 );
        accountPanel.add( accountSelect );
        accountPanel.add( accountSelectLabel );
        accountPanel.add( accountName );
        accountPanel.add( accountReference );
        accountPanel.add( accountBalance );
        mainDepositorPanel.add( accountPanel );

        JPanel transactionsPanel = new JPanel();
        transactionsPanel.setPreferredSize( new Dimension( 700, 300 ) );
        transactionsPanel.setLayout( null );
        transactionTableModel = new TransactionTableModel();
        transactionTable = new JTable( transactionTableModel );
        transactionTable.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        transactionTable.setPreferredScrollableViewportSize( new Dimension( 500, 500 ) );
        transactionTable.setFillsViewportHeight( true );
        JScrollPane transactionsScrollPane = new JScrollPane( transactionTable );
        mainDepositorPanel.add( transactionsScrollPane );

        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize( new Dimension( 700, 300 ) );
        buttonPanel.setLayout( new GridLayout( 2, 4 ) );

        addAccountButton = new JButton( "Add New Account" );
        addAccountButton.addActionListener( this );

        removeAccountButton = new JButton( "Delete Account" );
        removeAccountButton.addActionListener( this );

        JButton saveButton = new JButton( "Save" );
        saveButton.addActionListener( new ActionListener() {

            @Override
            public void actionPerformed ( ActionEvent e ) {
                JFrame saveFrame = new JFrame();
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory( new File( "./" ) );
                int userSelection = fileChooser.showSaveDialog( saveFrame );

                if ( userSelection == JFileChooser.APPROVE_OPTION ) {
                    File fileToSave = fileChooser.getSelectedFile();
                    manager.save( fileToSave.getAbsolutePath() );
                }
                saveFrame.dispose();

            }

        } );

        addTransactionButton = new JButton( "Add New Transaction" );
        addTransactionButton.addActionListener( this );

        makeWithdrawalButton = new JButton( "Make Withdrawal" );
        makeWithdrawalButton.addActionListener( this );

        makeDepositButton = new JButton( "Make Deposit" );
        makeDepositButton.addActionListener( this );

        editAccountNameButton = new JButton( "Edit Account Name" );
        editAccountNameButton.addActionListener( this );

        editInformationButton = new JButton( "Edit Personal Information" );
        editInformationButton.addActionListener( this );

        buttonPanel.add( addAccountButton );
        buttonPanel.add( removeAccountButton );
        buttonPanel.add( saveButton );
        buttonPanel.add( addTransactionButton );
        buttonPanel.add( makeWithdrawalButton );
        buttonPanel.add( makeDepositButton );
        buttonPanel.add( editAccountNameButton );
        buttonPanel.add( editInformationButton );
        mainDepositorPanel.add( buttonPanel );

        depositorFrame.add( mainDepositorPanel );
        depositorFrame.setVisible( true );

    }

    private class ItemChangeListener implements ItemListener {
        @Override
        public void itemStateChanged ( ItemEvent event ) {
            if ( event.getStateChange() == ItemEvent.SELECTED ) {
                Object item = event.getItem();
                String name = (String) item;
                selectedAccountName = name;
                Account a = manager.getAccountByName( name );
                accountName.setText( "Account Name: " + a.getName() );
                accountReference.setText( "Reference Number: " + a.getReference() );
                accountBalance.setText( "Account Balance: " + a.getBalance() );
                transactionTableModel.updateData();
                transactionTableModel.fireTableDataChanged();
            }
        }

    }

    public void makeEmployeeGUI () {
        employeeFrame = new JFrame();

        mainEmployeePanel = new JPanel( new GridLayout( 2, 1 ) );
        employeeFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        empPanel = new JPanel();
        empPanel.setLayout( new BoxLayout( empPanel, BoxLayout.Y_AXIS ) );
        empPanel.setPreferredSize( new Dimension( 700, 350 ) );

        empTableModel = new DepositorTableModel();
        tableEmployees = new JTable( empTableModel );
        tableEmployees.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        tableEmployees.setPreferredScrollableViewportSize( new Dimension( 500, 500 ) );
        tableEmployees.setFillsViewportHeight( true );

        tableEmployees.setShowGrid( true );
        JScrollPane listEmpScrollPane = new JScrollPane( tableEmployees );
        empPanel.add( listEmpScrollPane );
        mainEmployeePanel.add( empPanel );

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout( new GridLayout( 3, 3 ) );
        employeeSaveButton = new JButton( "Save" );
        addEmployeeButton = new JButton( "Add New Employee" );
        editEmployeeButton = new JButton( "Edit Account Info" );
        logoutButton = new JButton( "Logout" );
        addDepositorButton = new JButton( "Add New Depositor" );
        removeDepositorButton = new JButton( "Remove Depositor" );

        employeeSaveButton.addActionListener( this );
        addEmployeeButton.addActionListener( this );
        editEmployeeButton.addActionListener( this );
        logoutButton.addActionListener( this );
        addDepositorButton.addActionListener( this );
        removeDepositorButton.addActionListener( this );

        buttonPanel.add( employeeSaveButton );
        buttonPanel.add( addEmployeeButton );
        buttonPanel.add( editEmployeeButton );
        buttonPanel.add( logoutButton );
        buttonPanel.add( addDepositorButton );
        buttonPanel.add( removeDepositorButton );
        mainEmployeePanel.add( buttonPanel );
        employeeFrame.add( mainEmployeePanel );
        employeeFrame.pack();
        employeeFrame.setVisible( true );

    }

    private static class DepositorTableModel extends DefaultTableModel {
        private static final long serialVersionUID = 1L;

        private String[]          columnNames      = { "Username", "First Name", "Last Name", "Email", "Birthday" };

        private Object[][]        data;

        public DepositorTableModel () {
            Employee e = (Employee) manager.getCurrentUser();
            data = new Object[e.getDepositors().size()][columnNames.length];
            updateData();
        }

        private void updateData () {
            Employee e = (Employee) manager.getCurrentUser();
            data = new Object[e.getDepositors().size()][columnNames.length];
            for ( int i = 0; i < e.getDepositors().size(); i++ ) {
                Depositor d = e.getDepositors().get( i );
                for ( int j = 0; j < columnNames.length; j++ ) {
                    if ( j == 0 ) {
                        data[i][j] = d.getId();
                    }
                    else if ( j == 1 ) {
                        data[i][j] = d.getFirst();
                    }
                    else if ( j == 2 ) {
                        data[i][j] = d.getLast();
                    }
                    else if ( j == 3 ) {
                        data[i][j] = d.getEmail();
                    }
                    else {
                        data[i][j] = d.getBirthday();
                    }
                }
            }
        }

        @Override
        public String getColumnName ( int col ) {
            return columnNames[col];
        }

        @Override
        public int getRowCount () {
            if ( data == null ) {
                return 0;
            }
            else {
                Employee e = (Employee) manager.getCurrentUser();
                return e.getDepositors().size();
            }
        }

        @Override
        public int getColumnCount () {
            if ( data == null ) {
                return 0;
            }
            else {
                return columnNames.length;
            }
        }

        @Override
        public Object getValueAt ( int rowIndex, int columnIndex ) {
            if ( data == null ) {
                return null;
            }
            else {
                return data[rowIndex][columnIndex];
            }
        }
    }

    private class TransactionTableModel extends DefaultTableModel {
        private static final long serialVersionUID = 1L;

        private String[]          columnNames      = { "Transaction Description", "Amount", "Date" };

        private Object[][]        data;

        public TransactionTableModel () {
            Account a = manager.getAccountByName( selectedAccountName );
            if ( a != null ) {
                data = new Object[a.getTransactions().size()][columnNames.length];
                updateData();
            }
        }

        @Override
        public String getColumnName ( int col ) {
            return columnNames[col];
        }

        private void clearData () {
            for ( int i = 0; i < getRowCount(); i++ ) {
                for ( int j = 0; j < getColumnCount(); j++ ) {
                    data[i][j] = null;
                }
            }
        }

        private void updateData () {
            if ( ! ( manager.getCurrentUser() instanceof Depositor ) ) {
                throw new IllegalStateException( "Unable to update data" );
            }
            Account a = manager.getAccountByName( selectedAccountName );
            if ( a == null ) {
                Depositor d = (Depositor) manager.getCurrentUser();
                if ( d.getAccounts().size() != 0 ) {
                    selectedAccountName = d.getAccounts().get( 0 ).getName();
                    a = manager.getAccountByName( selectedAccountName );
                }
                else {
                    clearData();
                }
            }

            data = new Object[a.getTransactions().size()][columnNames.length];
            for ( int i = 0; i < a.getTransactions().size(); i++ ) {
                Transaction t = a.getTransactions().get( i );
                for ( int j = 0; j < columnNames.length; j++ ) {
                    if ( j == 0 ) {
                        data[i][j] = t.getDescription();
                    }
                    else if ( j == 1 ) {
                        data[i][j] = String.format( "%.2f", t.getAmount() );
                    }
                    else {
                        data[i][j] = t.getDate();
                    }
                }
            }
        }

        @Override
        public int getRowCount () {
            if ( data == null ) {
                return 0;
            }
            else {
                return data.length;
            }
        }

        @Override
        public int getColumnCount () {
            return columnNames.length;
        }

        @Override
        public Object getValueAt ( int rowIndex, int columnIndex ) {
            if ( data == null ) {
                return null;
            }
            else {
                return data[rowIndex][columnIndex];
            }
        }

        @Override
        public void setValueAt ( Object value, int row, int col ) {
            data[row][col] = value;
            fireTableCellUpdated( row, col );
        }

    }

    public static void main ( String[] args ) {

        manager = new BankManager();
        MyBankApplication app = new MyBankApplication();
        app.makeLoginGUI();
    }

}
