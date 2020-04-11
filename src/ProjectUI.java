/*

BOOKSTORE APPLICATION
COMP3005 PROJECT
BRADLEY CAMPBELL
101070833

*****Description*****
This application uses a postgres sql database as well as jbdc to query the database of the bookstore
The bookstore can add books, delete books, add publishers
It can also log users in and register them
It can query (search) for specific books based on title, isbn, num of pages, genre etc.
You can add books to your current basket as well as view books in the basket
By double clicking on any book, it will show all info on that book (including isbn, title, producer, author(s) etc.
Once you have added all the books to purchase to your basket, you can checkout
When checking out, add all information needed and the order will be created
You can see all previous orders made by the current customer.
By double clicking on the order, it will show specific details

If you are the owner, you can add and delete books as well as add publishers (please be sure to fill out all parts of the forms) (if adding book,the author must already exist in the database)
You can also order books (although that wouldn't really make sense if you own the bookstore, but I'm not going to stop you)
There is also the ability to see sales per genre, sales per author, sales per publisher. There must be atleast 1 sale to see it on the list
You can also see the sales vs expendatures where sales total is the total amount each book has sold for and the expendatures are the amount that has gone to paying publishers their percentage

There is currently only 1 warehouse which has an id of 1234567
The bookstore also will get new books from the publisher if stock gets lower than 10 (will get the amount from today to a month ago)


*/


import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public class ProjectUI extends javax.swing.JFrame {

    /**
     * Creates new form ProjectUI
     */
    public ProjectUI() {
        //create the UI
        initUI();
        //add all books to original view
        bookstart();
        //enable appropriate buttons at startup
        if(owner == false) {
            Main_View_Reports_Button.setVisible(false);
            Main_Add_Book_Button.setVisible(false);
            Main_Add_Publisher_Button.setVisible(false);
            Main_Delete_Book_Button.setVisible(false);
        } else{
            Main_View_Reports_Button.setVisible(true);
            Main_Add_Book_Button.setVisible(true);
            Main_Add_Publisher_Button.setVisible(true);
            Main_Delete_Book_Button.setVisible(true);
        }
        repaint();
    }



    //This Method Initializes all of the JSwing UI application
    //It gets the correct locations of buttons, fonts, styles, lists, labels, popups, etc.
    private void initUI() {
        //Initialize variables
        SearchGroup = new javax.swing.ButtonGroup();
        addPubPopup = new javax.swing.JDialog();
        Pub_ID_tf = new javax.swing.JTextField();
        Pub_ID = new javax.swing.JLabel();
        Pub_name = new javax.swing.JLabel();
        Pub_name_tf = new javax.swing.JTextField();
        Pub_address = new javax.swing.JLabel();
        Pub_address_tf = new javax.swing.JTextField();
        Pub_email = new javax.swing.JLabel();
        Pub_email_tf = new javax.swing.JTextField();
        Pub_ban = new javax.swing.JLabel();
        Pub_ban_tf = new javax.swing.JTextField();
        add_pub_title = new javax.swing.JLabel();
        add_pub_button = new javax.swing.JButton();
        Pub_phone = new javax.swing.JLabel();
        Pub_phone_tf = new javax.swing.JTextField();
        deleteBookPopup = new javax.swing.JDialog();
        Delete_Book_ISBN_tf = new javax.swing.JTextField();
        Delete_Book_ISBN = new javax.swing.JLabel();
        Delete_book_title = new javax.swing.JLabel();
        Delete_Book_Button = new javax.swing.JButton();
        addBookPopup = new javax.swing.JDialog();
        Add_Book_ISBN_tf1 = new javax.swing.JTextField();
        Add_Book_ISBN1 = new javax.swing.JLabel();
        Add_Book_title1 = new javax.swing.JLabel();
        Add_Book_title_tf1 = new javax.swing.JTextField();
        Add_Book_genre1 = new javax.swing.JLabel();
        Add_Book_genre_tf1 = new javax.swing.JTextField();
        Add_Book_nop1 = new javax.swing.JLabel();
        Add_Book_nop_tf1 = new javax.swing.JTextField();
        Add_Book_price1 = new javax.swing.JLabel();
        Add_Book_price_tf1 = new javax.swing.JTextField();
        Add_Book_authorid1 = new javax.swing.JLabel();
        Add_Book_authorid_tf1 = new javax.swing.JTextField();
        add_book_title1 = new javax.swing.JLabel();
        Add_Book_publisherid1 = new javax.swing.JLabel();
        Add_Book_publisherid_tf1 = new javax.swing.JTextField();
        Add_Book_Button = new javax.swing.JButton();
        Add_Book_count1 = new javax.swing.JLabel();
        Add_Book_count_tf1 = new javax.swing.JTextField();
        Add_Book_warehouseid1 = new javax.swing.JLabel();
        Add_Book_warehouseid_tf1 = new javax.swing.JTextField();
        Add_Book_percentage = new javax.swing.JLabel();
        Add_Book_percentage_tf1 = new javax.swing.JTextField();
        signInPopup = new javax.swing.JDialog();
        SignIn_ID_tf = new javax.swing.JTextField();
        SignIn_ID = new javax.swing.JLabel();
        SignIn_title = new javax.swing.JLabel();
        SignIn_popup_button = new javax.swing.JButton();
        InfoOnRegister = new javax.swing.JLabel();
        RegisterSignInButton = new javax.swing.JButton();
        viewOrdersPopup = new javax.swing.JDialog();
        view_orders_title = new javax.swing.JLabel();
        orders_close_button = new javax.swing.JButton();
        orders_scroll_pane = new javax.swing.JScrollPane();
        orders_list = new javax.swing.JList<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        bookInfoPopup = new javax.swing.JDialog();
        info_ISBN = new javax.swing.JLabel();
        info_title = new javax.swing.JLabel();
        info_genre = new javax.swing.JLabel();
        info_number_of_pages = new javax.swing.JLabel();
        info_author = new javax.swing.JLabel();
        book_info_title = new javax.swing.JLabel();
        book_info_okay_button = new javax.swing.JButton();
        info_publisher = new javax.swing.JLabel();
        info_author_answer = new javax.swing.JLabel();
        info_publisher_answer = new javax.swing.JLabel();
        info_number_of_pages_answer = new javax.swing.JLabel();
        info_genre_answer = new javax.swing.JLabel();
        info_title_answer = new javax.swing.JLabel();
        info_isbn_answer = new javax.swing.JLabel();
        info_price = new javax.swing.JLabel();
        info_price_answer = new javax.swing.JLabel();
        registerPopup = new javax.swing.JDialog();
        Register_ID_tf1 = new javax.swing.JTextField();
        Register_ID1 = new javax.swing.JLabel();
        Register_name1 = new javax.swing.JLabel();
        Register_Name_tf1 = new javax.swing.JTextField();
        Register_address1 = new javax.swing.JLabel();
        Register_Address_tf1 = new javax.swing.JTextField();
        Register_phone_number1 = new javax.swing.JLabel();
        Register_phone_number_tf1 = new javax.swing.JTextField();
        Register_email1 = new javax.swing.JLabel();
        Register_email_tf1 = new javax.swing.JTextField();
        register_title1 = new javax.swing.JLabel();
        Register_popup_button1 = new javax.swing.JButton();
        orderInfoPopup = new javax.swing.JDialog();
        order_info_number = new javax.swing.JLabel();
        order_info_pd = new javax.swing.JLabel();
        order_info_ds = new javax.swing.JLabel();
        order_info_add = new javax.swing.JLabel();
        order_info_email = new javax.swing.JLabel();
        book_info_title1 = new javax.swing.JLabel();
        order_info_button = new javax.swing.JButton();
        order_info_phone = new javax.swing.JLabel();
        order_info_ccn = new javax.swing.JLabel();
        order_info_number_answer = new javax.swing.JLabel();
        order_info_pd_answer = new javax.swing.JLabel();
        order_info_ds_answer = new javax.swing.JLabel();
        order_info_add_answer = new javax.swing.JLabel();
        order_info_email_answer = new javax.swing.JLabel();
        order_info_phone_answer = new javax.swing.JLabel();
        order_info_ccn_answer = new javax.swing.JLabel();
        order_info_isbn = new javax.swing.JLabel();
        order_info_isbn_answer = new javax.swing.JLabel();
        order_info_price = new javax.swing.JLabel();
        order_info_price_answer = new javax.swing.JLabel();
        viewBasketPopup = new javax.swing.JDialog();
        view_basket_title = new javax.swing.JLabel();
        basket_close_button = new javax.swing.JButton();
        basket_scroll_pane = new javax.swing.JScrollPane();
        basket_list = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        viewReportsPopup = new javax.swing.JDialog();
        view_report_title = new javax.swing.JLabel();
        view_report_close_button = new javax.swing.JButton();
        spg_button = new javax.swing.JButton();
        spa_button = new javax.swing.JButton();
        spp_button = new javax.swing.JButton();
        sve_button = new javax.swing.JButton();
        spgPopup = new javax.swing.JDialog();
        spg_title = new javax.swing.JLabel();
        spg_close = new javax.swing.JButton();
        spg_scroll = new javax.swing.JScrollPane();
        spg_list = new javax.swing.JList<>();
        count_label = new javax.swing.JLabel();
        price_label = new javax.swing.JLabel();
        genre_label = new javax.swing.JLabel();
        spaPopup = new javax.swing.JDialog();
        spa_title = new javax.swing.JLabel();
        spa_close = new javax.swing.JButton();
        spa_scroll = new javax.swing.JScrollPane();
        spa_list = new javax.swing.JList<>();
        count_label_a = new javax.swing.JLabel();
        price_label_a = new javax.swing.JLabel();
        author_label_a = new javax.swing.JLabel();
        sppPopup = new javax.swing.JDialog();
        spp_title = new javax.swing.JLabel();
        spp_close = new javax.swing.JButton();
        spp_scroll = new javax.swing.JScrollPane();
        spp_list = new javax.swing.JList<>();
        count_label_p = new javax.swing.JLabel();
        price_label_p = new javax.swing.JLabel();
        publisher_label_p = new javax.swing.JLabel();
        svePopup = new javax.swing.JDialog();
        sve_title = new javax.swing.JLabel();
        sve_close = new javax.swing.JButton();
        sales_label = new javax.swing.JLabel();
        sales_answer = new javax.swing.JLabel();
        expendatures_answer = new javax.swing.JLabel();
        expendatures_label = new javax.swing.JLabel();
        checkoutPopup = new javax.swing.JDialog();
        checkout_order_number = new javax.swing.JLabel();
        checkout_email = new javax.swing.JLabel();
        checkout_email_answer = new javax.swing.JTextField();
        checkout_phone_number = new javax.swing.JLabel();
        checkout_phone_number_answer = new javax.swing.JTextField();
        checkout_address = new javax.swing.JLabel();
        checkout_address_answer = new javax.swing.JTextField();
        checkout_creditcard = new javax.swing.JLabel();
        checkout_creditcard_answer = new javax.swing.JTextField();
        checkout_title = new javax.swing.JLabel();
        checkout_button = new javax.swing.JButton();
        checkout_date = new javax.swing.JLabel();
        checkout_price = new javax.swing.JLabel();
        checkout_order_number_answer = new javax.swing.JLabel();
        checkout_price_answer = new javax.swing.JLabel();
        checkout_date_answer = new javax.swing.JLabel();
        SearchPanel = new javax.swing.JPanel();
        Main_Search_Button = new javax.swing.JButton();
        ISBNButton = new javax.swing.JRadioButton();
        TitleButton = new javax.swing.JRadioButton();
        AuthorButton = new javax.swing.JRadioButton();
        GenreButton = new javax.swing.JRadioButton();
        SearchField = new javax.swing.JTextField();
        NumofPagesButton = new javax.swing.JRadioButton();
        Add_ISBN_tf = new javax.swing.JTextField();
        Main_Add_to_Basket_Button = new javax.swing.JButton();
        List_of_Books = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        Book_Search_Title = new javax.swing.JLabel();
        Main_Checkout_Button = new javax.swing.JButton();
        Main_View_Orders_Button = new javax.swing.JButton();
        Main_View_Reports_Button = new javax.swing.JButton();
        Add_to_Basket_Count = new javax.swing.JSpinner();
        Main_Add_Book_Button = new javax.swing.JButton();
        Main_Delete_Book_Button = new javax.swing.JButton();
        Main_Add_Publisher_Button = new javax.swing.JButton();
        isbn_title = new javax.swing.JLabel();
        title_title = new javax.swing.JLabel();
        view_basket_button = new javax.swing.JButton();

        //create popup for adding publishers
        addPubPopup.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addPubPopup.setAlwaysOnTop(true);
        addPubPopup.setMinimumSize(new java.awt.Dimension(443, 500));
        addPubPopup.setResizable(false);
        Pub_ID_tf.setToolTipText("");
        Pub_ID.setText("Id:");
        Pub_name.setText("Name:");
        Pub_address.setText("Address:");
        Pub_email.setText("Email:");
        Pub_ban.setText("Bank Account Number:");
        Pub_ban_tf.setToolTipText("16 digit number");
        add_pub_title.setFont(new java.awt.Font("Roland", 0, 24)); // NOI18N
        add_pub_title.setForeground(new java.awt.Color(255, 0, 51));
        add_pub_title.setText("ADD PUBLISHER");
        add_pub_button.setText("Add");
        add_pub_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_pub_buttonActionPerformed(evt);
            }
        });
        Pub_phone.setText("Phone Number(s):");
        Pub_phone_tf.setToolTipText("xxx-xxx-xxxx");

        //Combine all elements in the Add Publisher Popup
        javax.swing.GroupLayout addPubPopupLayout = new javax.swing.GroupLayout(addPubPopup.getContentPane());
        addPubPopup.getContentPane().setLayout(addPubPopupLayout);
        addPubPopupLayout.setHorizontalGroup(
                addPubPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(addPubPopupLayout.createSequentialGroup()
                                .addGap(101, 101, 101)
                                .addComponent(add_pub_title)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(addPubPopupLayout.createSequentialGroup()
                                .addContainerGap(11, Short.MAX_VALUE)
                                .addGroup(addPubPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(Pub_email)
                                        .addComponent(Pub_address)
                                        .addComponent(Pub_ID)
                                        .addComponent(Pub_name))
                                .addGap(102, 102, 102)
                                .addGroup(addPubPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addPubPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(Pub_name_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(Pub_ID_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(addPubPopupLayout.createSequentialGroup()
                                                .addGroup(addPubPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(Pub_address_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(Pub_email_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(2, 2, 2)))
                                .addContainerGap(77, Short.MAX_VALUE))
                        .addGroup(addPubPopupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(addPubPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(Pub_ban)
                                        .addComponent(Pub_phone))
                                .addGap(36, 36, 36)
                                .addGroup(addPubPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(Pub_phone_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(add_pub_button)
                                        .addComponent(Pub_ban_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        addPubPopupLayout.setVerticalGroup(
                addPubPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(addPubPopupLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(add_pub_title)
                                .addGap(18, 18, 18)
                                .addGroup(addPubPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Pub_ID_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Pub_ID))
                                .addGap(18, 18, 18)
                                .addGroup(addPubPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Pub_name_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Pub_name))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(addPubPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Pub_address_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Pub_address))
                                .addGap(18, 18, 18)
                                .addGroup(addPubPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Pub_email_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Pub_email))
                                .addGap(18, 18, 18)
                                .addGroup(addPubPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Pub_phone_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Pub_phone))
                                .addGap(17, 17, 17)
                                .addGroup(addPubPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Pub_ban_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Pub_ban))
                                .addGap(18, 18, 18)
                                .addComponent(add_pub_button)
                                .addContainerGap(56, Short.MAX_VALUE))
        );


        //create aspects of deleting a book popup
        deleteBookPopup.setMinimumSize(new java.awt.Dimension(218, 218));
        Delete_Book_ISBN_tf.setToolTipText("7 digit number");
        Delete_Book_ISBN.setText("ISBN:");
        Delete_book_title.setFont(new java.awt.Font("Roland", 0, 24)); // NOI18N
        Delete_book_title.setForeground(new java.awt.Color(255, 0, 51));
        Delete_book_title.setText("DELETE BOOK");
        Delete_Book_Button.setText("Delete");
        Delete_Book_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Delete_Book_ButtonActionPerformed(evt);
            }
        });

        //combine all aspects of the delete book popup
        javax.swing.GroupLayout deleteBookPopupLayout = new javax.swing.GroupLayout(deleteBookPopup.getContentPane());
        deleteBookPopup.getContentPane().setLayout(deleteBookPopupLayout);
        deleteBookPopupLayout.setHorizontalGroup(
                deleteBookPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(deleteBookPopupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(deleteBookPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(deleteBookPopupLayout.createSequentialGroup()
                                                .addComponent(Delete_Book_ISBN)
                                                .addGap(18, 18, 18)
                                                .addGroup(deleteBookPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(deleteBookPopupLayout.createSequentialGroup()
                                                                .addGap(10, 10, 10)
                                                                .addComponent(Delete_Book_Button))
                                                        .addComponent(Delete_Book_ISBN_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(Delete_book_title))
                                .addContainerGap(16, Short.MAX_VALUE))
        );
        deleteBookPopupLayout.setVerticalGroup(
                deleteBookPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(deleteBookPopupLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(Delete_book_title)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(deleteBookPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Delete_Book_ISBN_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Delete_Book_ISBN))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Delete_Book_Button)
                                .addContainerGap(22, Short.MAX_VALUE))
        );


        //create aspects for adding a book popup
        addBookPopup.setMinimumSize(new java.awt.Dimension(443, 600));
        addBookPopup.setResizable(false);
        Add_Book_ISBN_tf1.setToolTipText("7 digit number");
        Add_Book_ISBN1.setText("ISBN:");
        Add_Book_title1.setText("Title:");
        Add_Book_genre1.setText("Genre:");
        Add_Book_nop1.setText("Number of Pages:");
        Add_Book_price1.setText("Price:");
        Add_Book_authorid1.setText("Author Id(s):");
        Add_Book_authorid_tf1.setToolTipText("e.g:   11111, 44444, 55555");
        add_book_title1.setFont(new java.awt.Font("Roland", 0, 24)); // NOI18N
        add_book_title1.setForeground(new java.awt.Color(255, 0, 51));
        add_book_title1.setText("ADD BOOK");
        Add_Book_publisherid1.setText("Publisher Id:");
        Add_Book_Button.setText("Add");
        Add_Book_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add_Book_ButtonActionPerformed(evt);
            }
        });
        Add_Book_count1.setText("Number in Stock:");
        Add_Book_warehouseid1.setText("Warehouse Id:");
        Add_Book_warehouseid_tf1.setToolTipText("1234567 (only one warehouse)");
        Add_Book_percentage.setText("Percentage:");
        Add_Book_percentage_tf1.setToolTipText("percentage of sale given to publisher");

        //combine elements of adding a book popup
        javax.swing.GroupLayout addBookPopupLayout = new javax.swing.GroupLayout(addBookPopup.getContentPane());
        addBookPopup.getContentPane().setLayout(addBookPopupLayout);
        addBookPopupLayout.setHorizontalGroup(
                addBookPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addBookPopupLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(add_book_title1)
                                .addGap(142, 142, 142))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addBookPopupLayout.createSequentialGroup()
                                .addContainerGap(27, Short.MAX_VALUE)
                                .addGroup(addBookPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(addBookPopupLayout.createSequentialGroup()
                                                .addComponent(Add_Book_percentage)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(Add_Book_percentage_tf1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(addBookPopupLayout.createSequentialGroup()
                                                .addComponent(Add_Book_warehouseid1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(Add_Book_warehouseid_tf1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, addBookPopupLayout.createSequentialGroup()
                                                .addComponent(Add_Book_count1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(Add_Book_count_tf1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, addBookPopupLayout.createSequentialGroup()
                                                .addComponent(Add_Book_publisherid1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(Add_Book_publisherid_tf1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, addBookPopupLayout.createSequentialGroup()
                                                .addComponent(Add_Book_authorid1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(Add_Book_authorid_tf1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, addBookPopupLayout.createSequentialGroup()
                                                .addComponent(Add_Book_price1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(Add_Book_price_tf1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, addBookPopupLayout.createSequentialGroup()
                                                .addComponent(Add_Book_nop1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                                                .addComponent(Add_Book_nop_tf1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(addBookPopupLayout.createSequentialGroup()
                                                .addComponent(Add_Book_genre1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(Add_Book_genre_tf1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, addBookPopupLayout.createSequentialGroup()
                                                .addComponent(Add_Book_title1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(Add_Book_title_tf1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(addBookPopupLayout.createSequentialGroup()
                                                .addComponent(Add_Book_ISBN1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(Add_Book_ISBN_tf1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(19, 19, 19))
                        .addGroup(addBookPopupLayout.createSequentialGroup()
                                .addGap(181, 181, 181)
                                .addComponent(Add_Book_Button)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        addBookPopupLayout.setVerticalGroup(
                addBookPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(addBookPopupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(add_book_title1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(addBookPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Add_Book_ISBN_tf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Add_Book_ISBN1))
                                .addGap(18, 18, 18)
                                .addGroup(addBookPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Add_Book_title_tf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Add_Book_title1))
                                .addGap(18, 18, 18)
                                .addGroup(addBookPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Add_Book_genre_tf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Add_Book_genre1))
                                .addGap(18, 18, 18)
                                .addGroup(addBookPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Add_Book_nop_tf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Add_Book_nop1))
                                .addGap(18, 18, 18)
                                .addGroup(addBookPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Add_Book_price_tf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Add_Book_price1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(addBookPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Add_Book_authorid_tf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Add_Book_authorid1))
                                .addGap(18, 18, 18)
                                .addGroup(addBookPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Add_Book_publisherid_tf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Add_Book_publisherid1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(addBookPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Add_Book_count_tf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Add_Book_count1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(addBookPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Add_Book_warehouseid_tf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Add_Book_warehouseid1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(addBookPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Add_Book_percentage_tf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Add_Book_percentage))
                                .addGap(18, 18, 18)
                                .addComponent(Add_Book_Button)
                                .addContainerGap(39, Short.MAX_VALUE))
        );


        //create elements for the sign in popup
        signInPopup.setMinimumSize(new java.awt.Dimension(443, 500));
        SignIn_ID.setText("Id:");
        SignIn_title.setFont(new java.awt.Font("Roland", 0, 24)); // NOI18N
        SignIn_title.setForeground(new java.awt.Color(255, 0, 51));
        SignIn_title.setText("SIGN IN");
        SignIn_popup_button.setText("Sign In");
        SignIn_popup_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignIn_popup_buttonActionPerformed(evt);
            }
        });
        InfoOnRegister.setText("Don't have an account? Register Here:");
        RegisterSignInButton.setText("Register");
        RegisterSignInButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegisterSignInButtonActionPerformed(evt);
            }
        });
        //combine aspects and format sign in popup
        javax.swing.GroupLayout signInPopupLayout = new javax.swing.GroupLayout(signInPopup.getContentPane());
        signInPopup.getContentPane().setLayout(signInPopupLayout);
        signInPopupLayout.setHorizontalGroup(
                signInPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(signInPopupLayout.createSequentialGroup()
                                .addGap(167, 167, 167)
                                .addComponent(SignIn_popup_button)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(signInPopupLayout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addGroup(signInPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(signInPopupLayout.createSequentialGroup()
                                                .addComponent(InfoOnRegister)
                                                .addGap(18, 18, 18)
                                                .addComponent(RegisterSignInButton)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(signInPopupLayout.createSequentialGroup()
                                                .addGap(0, 61, Short.MAX_VALUE)
                                                .addComponent(SignIn_ID)
                                                .addGap(38, 38, 38)
                                                .addGroup(signInPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(SignIn_title)
                                                        .addComponent(SignIn_ID_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(149, 149, 149))))
        );
        signInPopupLayout.setVerticalGroup(
                signInPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(signInPopupLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(SignIn_title)
                                .addGap(37, 37, 37)
                                .addGroup(signInPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(SignIn_ID_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(SignIn_ID))
                                .addGap(37, 37, 37)
                                .addComponent(SignIn_popup_button)
                                .addGap(66, 66, 66)
                                .addGroup(signInPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(InfoOnRegister)
                                        .addComponent(RegisterSignInButton))
                                .addContainerGap(85, Short.MAX_VALUE))
        );

        //create view orders popup and its elements
        viewOrdersPopup.setMinimumSize(new java.awt.Dimension(443, 500));
        view_orders_title.setFont(new java.awt.Font("Roland", 0, 24)); // NOI18N
        view_orders_title.setForeground(new java.awt.Color(255, 0, 51));
        view_orders_title.setText("ORDERS");
        orders_close_button.setText("Close");
        orders_close_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orders_close_buttonActionPerformed(evt);
            }
        });
        orders_list.setModel(ordersmodel);
        orders_list.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                orders_listMouseClicked(evt);
            }
        });
        orders_scroll_pane.setViewportView(orders_list);
        jLabel4.setText("Order Number");
        jLabel5.setText("Date Ordered");
        jLabel6.setText("Delivery Status");

        //Combine all elements to the correct formating for the view orders popup
        javax.swing.GroupLayout viewOrdersPopupLayout = new javax.swing.GroupLayout(viewOrdersPopup.getContentPane());
        viewOrdersPopup.getContentPane().setLayout(viewOrdersPopupLayout);
        viewOrdersPopupLayout.setHorizontalGroup(
                viewOrdersPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(viewOrdersPopupLayout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addGroup(viewOrdersPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(viewOrdersPopupLayout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(viewOrdersPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(orders_close_button, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(view_orders_title)
                                                        .addGroup(viewOrdersPopupLayout.createSequentialGroup()
                                                                .addGap(22, 22, 22)
                                                                .addComponent(jLabel5)
                                                                .addGap(36, 36, 36)
                                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addContainerGap(89, Short.MAX_VALUE))
                                        .addGroup(viewOrdersPopupLayout.createSequentialGroup()
                                                .addComponent(orders_scroll_pane, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(69, Short.MAX_VALUE))))
        );
        viewOrdersPopupLayout.setVerticalGroup(
                viewOrdersPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(viewOrdersPopupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(view_orders_title)
                                .addGap(8, 8, 8)
                                .addGroup(viewOrdersPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel6))
                                .addGap(6, 6, 6)
                                .addComponent(orders_scroll_pane, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(orders_close_button)
                                .addGap(59, 59, 59))
        );


        //create the book info popup and its elements
        bookInfoPopup.setMinimumSize(new java.awt.Dimension(443, 500));
        info_ISBN.setText("ISBN");
        info_title.setText("Title:");
        info_genre.setText("Genre:");
        info_number_of_pages.setText("Number of Pages:");
        info_author.setText("Author(s):");
        book_info_title.setFont(new java.awt.Font("Roland", 0, 24)); // NOI18N
        book_info_title.setForeground(new java.awt.Color(255, 0, 51));
        book_info_title.setText("Book Info");
        book_info_okay_button.setText("Okay");
        book_info_okay_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                book_info_okay_buttonActionPerformed(evt);
            }
        });
        info_publisher.setText("Publisher:");
        info_price.setText("Price:");

        //combine elements into proper layout for the book info popup
        javax.swing.GroupLayout bookInfoPopupLayout = new javax.swing.GroupLayout(bookInfoPopup.getContentPane());
        bookInfoPopup.getContentPane().setLayout(bookInfoPopupLayout);
        bookInfoPopupLayout.setHorizontalGroup(
                bookInfoPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(bookInfoPopupLayout.createSequentialGroup()
                                .addGap(182, 182, 182)
                                .addComponent(book_info_okay_button)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(bookInfoPopupLayout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addGroup(bookInfoPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(bookInfoPopupLayout.createSequentialGroup()
                                                .addComponent(info_price)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(info_price_answer, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bookInfoPopupLayout.createSequentialGroup()
                                                .addComponent(info_ISBN)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(bookInfoPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bookInfoPopupLayout.createSequentialGroup()
                                                                .addComponent(book_info_title)
                                                                .addGap(150, 150, 150))
                                                        .addComponent(info_isbn_answer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bookInfoPopupLayout.createSequentialGroup()
                                                .addGroup(bookInfoPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(info_number_of_pages)
                                                        .addComponent(info_author))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                                                .addGroup(bookInfoPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(info_author_answer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(info_number_of_pages_answer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(bookInfoPopupLayout.createSequentialGroup()
                                                .addComponent(info_title)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(info_title_answer, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(bookInfoPopupLayout.createSequentialGroup()
                                                .addComponent(info_publisher)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(info_publisher_answer, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(bookInfoPopupLayout.createSequentialGroup()
                                                .addComponent(info_genre)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(info_genre_answer, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        bookInfoPopupLayout.setVerticalGroup(
                bookInfoPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(bookInfoPopupLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(book_info_title)
                                .addGap(40, 40, 40)
                                .addGroup(bookInfoPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(info_ISBN)
                                        .addComponent(info_isbn_answer))
                                .addGap(18, 18, 18)
                                .addGroup(bookInfoPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(info_title)
                                        .addComponent(info_title_answer))
                                .addGap(18, 18, 18)
                                .addGroup(bookInfoPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(info_genre)
                                        .addComponent(info_genre_answer))
                                .addGap(18, 18, 18)
                                .addGroup(bookInfoPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(info_number_of_pages)
                                        .addComponent(info_number_of_pages_answer))
                                .addGap(18, 18, 18)
                                .addGroup(bookInfoPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(info_author)
                                        .addComponent(info_author_answer))
                                .addGap(18, 18, 18)
                                .addGroup(bookInfoPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(info_publisher)
                                        .addComponent(info_publisher_answer))
                                .addGap(18, 18, 18)
                                .addGroup(bookInfoPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(info_price)
                                        .addComponent(info_price_answer))
                                .addGap(9, 9, 9)
                                .addComponent(book_info_okay_button)
                                .addContainerGap(62, Short.MAX_VALUE))
        );


        //create elements for the register customer popup
        registerPopup.setMinimumSize(new java.awt.Dimension(443, 500));
        Register_ID_tf1.setToolTipText("Up to 12 digit username");
        Register_ID1.setText("Id:");
        Register_name1.setText("Name:");
        Register_address1.setText("Address:");
        Register_phone_number1.setText("Phone Number:");
        Register_phone_number_tf1.setToolTipText("XXX-XXX-XXXX");
        Register_email1.setText("Email:");
        register_title1.setFont(new java.awt.Font("Roland", 0, 24)); // NOI18N
        register_title1.setForeground(new java.awt.Color(255, 0, 51));
        register_title1.setText("REGISTER");
        Register_popup_button1.setText("Register");
        Register_popup_button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Register_popup_button1ActionPerformed(evt);
            }
        });

        //combine elements for the register popup
        javax.swing.GroupLayout registerPopupLayout = new javax.swing.GroupLayout(registerPopup.getContentPane());
        registerPopup.getContentPane().setLayout(registerPopupLayout);
        registerPopupLayout.setHorizontalGroup(
                registerPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(registerPopupLayout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addGroup(registerPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(registerPopupLayout.createSequentialGroup()
                                                .addComponent(Register_email1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(Register_email_tf1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(registerPopupLayout.createSequentialGroup()
                                                .addComponent(Register_phone_number1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                                                .addComponent(Register_phone_number_tf1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(registerPopupLayout.createSequentialGroup()
                                                .addComponent(Register_name1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(Register_Name_tf1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registerPopupLayout.createSequentialGroup()
                                                .addComponent(Register_ID1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(registerPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(register_title1)
                                                        .addComponent(Register_ID_tf1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registerPopupLayout.createSequentialGroup()
                                                .addComponent(Register_address1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(Register_Address_tf1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
                        .addGroup(registerPopupLayout.createSequentialGroup()
                                .addGap(183, 183, 183)
                                .addComponent(Register_popup_button1)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        registerPopupLayout.setVerticalGroup(
                registerPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(registerPopupLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(register_title1)
                                .addGap(37, 37, 37)
                                .addGroup(registerPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Register_ID_tf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Register_ID1))
                                .addGap(18, 18, 18)
                                .addGroup(registerPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Register_Name_tf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Register_name1))
                                .addGap(18, 18, 18)
                                .addGroup(registerPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Register_Address_tf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Register_address1))
                                .addGap(18, 18, 18)
                                .addGroup(registerPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Register_phone_number_tf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Register_phone_number1))
                                .addGap(18, 18, 18)
                                .addGroup(registerPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Register_email_tf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Register_email1))
                                .addGap(29, 29, 29)
                                .addComponent(Register_popup_button1)
                                .addContainerGap(79, Short.MAX_VALUE))
        );


        //create elements for the order info popup
        orderInfoPopup.setMinimumSize(new java.awt.Dimension(443, 500));
        order_info_number.setText("Order Number:");
        order_info_pd.setText("Purchase Date:");
        order_info_ds.setText("Delivery Status:");
        order_info_add.setText("Address:");
        order_info_email.setText("Email:");
        book_info_title1.setFont(new java.awt.Font("Roland", 0, 24)); // NOI18N
        book_info_title1.setForeground(new java.awt.Color(255, 0, 51));
        book_info_title1.setText("Order Info");
        order_info_button.setText("Okay");
        order_info_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                order_info_buttonActionPerformed(evt);
            }
        });
        order_info_phone.setText("Phone:");
        order_info_ccn.setText("Credit Card #:");
        order_info_number_answer.setText("test");
        order_info_pd_answer.setText("tets");
        order_info_ds_answer.setText("test");
        order_info_add_answer.setText("test");
        order_info_email_answer.setText("test");
        order_info_phone_answer.setText("test");
        order_info_ccn_answer.setText("test");
        order_info_isbn.setText("ISBN(s):");
        order_info_isbn_answer.setText("test");
        order_info_price.setText("Total Price:");
        order_info_price_answer.setText("test");

        //create format and layout for the order info popup
        javax.swing.GroupLayout orderInfoPopupLayout = new javax.swing.GroupLayout(orderInfoPopup.getContentPane());
        orderInfoPopup.getContentPane().setLayout(orderInfoPopupLayout);
        orderInfoPopupLayout.setHorizontalGroup(
                orderInfoPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(orderInfoPopupLayout.createSequentialGroup()
                                .addGap(37, 139, Short.MAX_VALUE)
                                .addComponent(book_info_title1)
                                .addGap(160, 160, 160))
                        .addGroup(orderInfoPopupLayout.createSequentialGroup()
                                .addGroup(orderInfoPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(orderInfoPopupLayout.createSequentialGroup()
                                                .addGap(184, 184, 184)
                                                .addComponent(order_info_button))
                                        .addGroup(orderInfoPopupLayout.createSequentialGroup()
                                                .addGap(41, 41, 41)
                                                .addGroup(orderInfoPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, orderInfoPopupLayout.createSequentialGroup()
                                                                .addComponent(order_info_number)
                                                                .addGap(26, 26, 26))
                                                        .addGroup(orderInfoPopupLayout.createSequentialGroup()
                                                                .addGroup(orderInfoPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(order_info_ccn)
                                                                        .addComponent(order_info_pd)
                                                                        .addComponent(order_info_phone)
                                                                        .addComponent(order_info_ds)
                                                                        .addComponent(order_info_isbn)
                                                                        .addComponent(order_info_add)
                                                                        .addComponent(order_info_email)
                                                                        .addComponent(order_info_price))
                                                                .addGap(21, 21, 21)))
                                                .addGroup(orderInfoPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(order_info_price_answer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(order_info_number_answer, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(order_info_pd_answer, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(order_info_ds_answer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(order_info_add_answer, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(order_info_email_answer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(order_info_phone_answer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(order_info_ccn_answer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(order_info_isbn_answer, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE))))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        orderInfoPopupLayout.setVerticalGroup(
                orderInfoPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(orderInfoPopupLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(book_info_title1)
                                .addGap(1, 1, 1)
                                .addGroup(orderInfoPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(order_info_number)
                                        .addComponent(order_info_number_answer))
                                .addGap(18, 18, 18)
                                .addGroup(orderInfoPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(order_info_pd)
                                        .addComponent(order_info_pd_answer))
                                .addGap(18, 18, 18)
                                .addGroup(orderInfoPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(order_info_ds)
                                        .addComponent(order_info_ds_answer))
                                .addGap(18, 18, 18)
                                .addGroup(orderInfoPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(order_info_add_answer)
                                        .addComponent(order_info_add))
                                .addGap(18, 18, 18)
                                .addGroup(orderInfoPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(order_info_email_answer)
                                        .addComponent(order_info_email))
                                .addGap(18, 18, 18)
                                .addGroup(orderInfoPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(order_info_phone_answer)
                                        .addComponent(order_info_phone))
                                .addGap(18, 18, 18)
                                .addGroup(orderInfoPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(order_info_ccn_answer)
                                        .addComponent(order_info_ccn))
                                .addGap(18, 18, 18)
                                .addGroup(orderInfoPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(order_info_isbn_answer)
                                        .addComponent(order_info_isbn))
                                .addGap(18, 18, 18)
                                .addGroup(orderInfoPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(order_info_price_answer)
                                        .addComponent(order_info_price))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                                .addComponent(order_info_button)
                                .addContainerGap())
        );


        //create elements for the view basket popup
        viewBasketPopup.setMinimumSize(new java.awt.Dimension(443, 500));
        view_basket_title.setFont(new java.awt.Font("Roland", 0, 24)); // NOI18N
        view_basket_title.setForeground(new java.awt.Color(255, 0, 51));
        view_basket_title.setText("BASKET");
        basket_close_button.setText("Close");
        basket_close_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                basket_close_buttonActionPerformed(evt);
            }
        });
        basket_list.setModel(basketmodel);
        basket_list.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                basket_listMouseClicked(evt);
            }
        });
        basket_scroll_pane.setViewportView(basket_list);
        jLabel1.setText("ISBN");
        jLabel2.setText("Count");
        jLabel3.setText("Title");

        //add elements to layout for view basket popup
        javax.swing.GroupLayout viewBasketPopupLayout = new javax.swing.GroupLayout(viewBasketPopup.getContentPane());
        viewBasketPopup.getContentPane().setLayout(viewBasketPopupLayout);
        viewBasketPopupLayout.setHorizontalGroup(
                viewBasketPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(viewBasketPopupLayout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addGroup(viewBasketPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(viewBasketPopupLayout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(viewBasketPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewBasketPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(view_basket_title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(basket_close_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewBasketPopupLayout.createSequentialGroup()
                                                                .addComponent(jLabel2)
                                                                .addGap(56, 56, 56)
                                                                .addComponent(jLabel3)))
                                                .addGap(157, 157, 157))
                                        .addGroup(viewBasketPopupLayout.createSequentialGroup()
                                                .addComponent(basket_scroll_pane, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(69, Short.MAX_VALUE))))
        );
        viewBasketPopupLayout.setVerticalGroup(
                viewBasketPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(viewBasketPopupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(view_basket_title)
                                .addGap(9, 9, 9)
                                .addGroup(viewBasketPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3))
                                .addGap(5, 5, 5)
                                .addComponent(basket_scroll_pane, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(basket_close_button)
                                .addGap(59, 59, 59))
        );


        //create view reports popup
        viewReportsPopup.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        viewReportsPopup.setAlwaysOnTop(true);
        viewReportsPopup.setMinimumSize(new java.awt.Dimension(443, 500));
        viewReportsPopup.setResizable(false);
        view_report_title.setFont(new java.awt.Font("Roland", 0, 24)); // NOI18N
        view_report_title.setForeground(new java.awt.Color(255, 0, 51));
        view_report_title.setText("REPORTS");
        view_report_close_button.setText("Close");
        view_report_close_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                view_report_close_buttonActionPerformed(evt);
            }
        });
        spg_button.setText("Sales Per Genre");
        spg_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spg_buttonActionPerformed(evt);
            }
        });
        spa_button.setText("Sales Per Author");
        spa_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spa_buttonActionPerformed(evt);
            }
        });
        spp_button.setText("Sales Per Publisher");
        spp_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spp_buttonActionPerformed(evt);
            }
        });
        sve_button.setText("Sales vs. Expendatures");
        sve_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sve_buttonActionPerformed(evt);
            }
        });

        //Create layout of view reports popup
        javax.swing.GroupLayout viewReportsPopupLayout = new javax.swing.GroupLayout(viewReportsPopup.getContentPane());
        viewReportsPopup.getContentPane().setLayout(viewReportsPopupLayout);
        viewReportsPopupLayout.setHorizontalGroup(
                viewReportsPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(viewReportsPopupLayout.createSequentialGroup()
                                .addGroup(viewReportsPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(viewReportsPopupLayout.createSequentialGroup()
                                                .addGap(130, 130, 130)
                                                .addGroup(viewReportsPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(sve_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(viewReportsPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addComponent(view_report_title, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGroup(viewReportsPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(spa_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(spp_button, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                                                                        .addComponent(spg_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                                        .addGroup(viewReportsPopupLayout.createSequentialGroup()
                                                .addGap(170, 170, 170)
                                                .addComponent(view_report_close_button)))
                                .addContainerGap(140, Short.MAX_VALUE))
        );
        viewReportsPopupLayout.setVerticalGroup(
                viewReportsPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(viewReportsPopupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(view_report_title)
                                .addGap(35, 35, 35)
                                .addComponent(spg_button)
                                .addGap(30, 30, 30)
                                .addComponent(spa_button)
                                .addGap(33, 33, 33)
                                .addComponent(spp_button)
                                .addGap(29, 29, 29)
                                .addComponent(sve_button)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                                .addComponent(view_report_close_button)
                                .addGap(41, 41, 41))
        );


        //create sales per genre popup
        spgPopup.setAlwaysOnTop(true);
        spgPopup.setMinimumSize(new java.awt.Dimension(443, 500));
        spg_title.setFont(new java.awt.Font("Roland", 0, 24)); // NOI18N
        spg_title.setForeground(new java.awt.Color(255, 0, 51));
        spg_title.setText("Sales Per Genre");
        spg_close.setText("Close");
        spg_close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spg_closeActionPerformed(evt);
            }
        });
        spg_list.setModel(spgmodel);
        spg_scroll.setViewportView(spg_list);
        count_label.setText("Count");
        price_label.setText("Price");
        genre_label.setText("Genre");

        //format elements in sales per genre popup
        javax.swing.GroupLayout spgPopupLayout = new javax.swing.GroupLayout(spgPopup.getContentPane());
        spgPopup.getContentPane().setLayout(spgPopupLayout);
        spgPopupLayout.setHorizontalGroup(
                spgPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(spgPopupLayout.createSequentialGroup()
                                .addGroup(spgPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(spgPopupLayout.createSequentialGroup()
                                                .addGap(107, 107, 107)
                                                .addComponent(spg_close, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(spgPopupLayout.createSequentialGroup()
                                                .addGap(67, 67, 67)
                                                .addGroup(spgPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(spgPopupLayout.createSequentialGroup()
                                                                .addComponent(count_label)
                                                                .addGap(44, 44, 44)
                                                                .addComponent(price_label)
                                                                .addGap(69, 69, 69)
                                                                .addComponent(genre_label))
                                                        .addComponent(spg_scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(spgPopupLayout.createSequentialGroup()
                                                .addGap(110, 110, 110)
                                                .addComponent(spg_title)))
                                .addContainerGap(69, Short.MAX_VALUE))
        );
        spgPopupLayout.setVerticalGroup(
                spgPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(spgPopupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(spg_title)
                                .addGap(7, 7, 7)
                                .addGroup(spgPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(count_label)
                                        .addComponent(price_label)
                                        .addComponent(genre_label))
                                .addGap(7, 7, 7)
                                .addComponent(spg_scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(spg_close)
                                .addGap(59, 59, 59))
        );


        //create sales per author popup
        spaPopup.setAlwaysOnTop(true);
        spaPopup.setMinimumSize(new java.awt.Dimension(443, 500));
        spa_title.setFont(new java.awt.Font("Roland", 0, 24)); // NOI18N
        spa_title.setForeground(new java.awt.Color(255, 0, 51));
        spa_title.setText("Sales Per Author");
        spa_close.setText("Close");
        spa_close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spa_closeActionPerformed(evt);
            }
        });
        spa_list.setModel(spamodel);
        spa_scroll.setViewportView(spa_list);
        count_label_a.setText("Count");
        price_label_a.setText("Price");
        author_label_a.setText("Author");

        //make formating of sales per author popup
        javax.swing.GroupLayout spaPopupLayout = new javax.swing.GroupLayout(spaPopup.getContentPane());
        spaPopup.getContentPane().setLayout(spaPopupLayout);
        spaPopupLayout.setHorizontalGroup(
                spaPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(spaPopupLayout.createSequentialGroup()
                                .addGroup(spaPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(spaPopupLayout.createSequentialGroup()
                                                .addGap(107, 107, 107)
                                                .addComponent(spa_close, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(spaPopupLayout.createSequentialGroup()
                                                .addGap(67, 67, 67)
                                                .addGroup(spaPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(spaPopupLayout.createSequentialGroup()
                                                                .addComponent(count_label_a)
                                                                .addGap(64, 64, 64)
                                                                .addComponent(price_label_a)
                                                                .addGap(63, 63, 63)
                                                                .addComponent(author_label_a))
                                                        .addComponent(spa_scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(69, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, spaPopupLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(spa_title)
                                .addGap(101, 101, 101))
        );
        spaPopupLayout.setVerticalGroup(
                spaPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(spaPopupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(spa_title)
                                .addGap(8, 8, 8)
                                .addGroup(spaPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(count_label_a)
                                        .addComponent(price_label_a)
                                        .addComponent(author_label_a))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spa_scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(spa_close)
                                .addGap(59, 59, 59))
        );


        //create sales per producer popup
        sppPopup.setAlwaysOnTop(true);
        sppPopup.setMinimumSize(new java.awt.Dimension(443, 500));
        spp_title.setFont(new java.awt.Font("Roland", 0, 24)); // NOI18N
        spp_title.setForeground(new java.awt.Color(255, 0, 51));
        spp_title.setText("Sales Per Publisher");
        spp_close.setText("Close");
        spp_close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spp_closeActionPerformed(evt);
            }
        });
        spp_list.setModel(sppmodel);
        spp_scroll.setViewportView(spp_list);
        count_label_p.setText("Count");
        price_label_p.setText("Price");
        publisher_label_p.setText("Publisher");

        //create layout for sales per producer
        javax.swing.GroupLayout sppPopupLayout = new javax.swing.GroupLayout(sppPopup.getContentPane());
        sppPopup.getContentPane().setLayout(sppPopupLayout);
        sppPopupLayout.setHorizontalGroup(
                sppPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(sppPopupLayout.createSequentialGroup()
                                .addGroup(sppPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(sppPopupLayout.createSequentialGroup()
                                                .addGap(107, 107, 107)
                                                .addComponent(spp_close, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(sppPopupLayout.createSequentialGroup()
                                                .addGap(67, 67, 67)
                                                .addGroup(sppPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(sppPopupLayout.createSequentialGroup()
                                                                .addComponent(count_label_p)
                                                                .addGap(53, 53, 53)
                                                                .addComponent(price_label_p)
                                                                .addGap(75, 75, 75)
                                                                .addComponent(publisher_label_p))
                                                        .addComponent(spp_scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(69, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sppPopupLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(spp_title)
                                .addGap(83, 83, 83))
        );
        sppPopupLayout.setVerticalGroup(
                sppPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(sppPopupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(spp_title)
                                .addGap(8, 8, 8)
                                .addGroup(sppPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(count_label_p)
                                        .addComponent(price_label_p)
                                        .addComponent(publisher_label_p))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spp_scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(spp_close)
                                .addGap(59, 59, 59))
        );


        //create sales vs expendatures popup
        svePopup.setAlwaysOnTop(true);
        svePopup.setMinimumSize(new java.awt.Dimension(443, 500));
        sve_title.setFont(new java.awt.Font("Roland", 0, 24)); // NOI18N
        sve_title.setForeground(new java.awt.Color(255, 0, 51));
        sve_title.setText("Sales vs Expendatures");
        sve_close.setText("Close");
        sve_close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sve_closeActionPerformed(evt);
            }
        });
        sales_label.setFont(new java.awt.Font("Unispace", 0, 18)); // NOI18N
        sales_label.setText("Sales:");
        sales_answer.setFont(new java.awt.Font("Unispace", 0, 18)); // NOI18N
        sales_answer.setText("$_____");
        expendatures_answer.setFont(new java.awt.Font("Unispace", 0, 18)); // NOI18N
        expendatures_answer.setText("$_____");
        expendatures_label.setFont(new java.awt.Font("Unispace", 0, 18)); // NOI18N
        expendatures_label.setText("Expendatures:");

        //create formatting and spacing of sales vs expendatures popup
        javax.swing.GroupLayout svePopupLayout = new javax.swing.GroupLayout(svePopup.getContentPane());
        svePopup.getContentPane().setLayout(svePopupLayout);
        svePopupLayout.setHorizontalGroup(
                svePopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, svePopupLayout.createSequentialGroup()
                                .addContainerGap(37, Short.MAX_VALUE)
                                .addComponent(sve_title)
                                .addGap(59, 59, 59))
                        .addGroup(svePopupLayout.createSequentialGroup()
                                .addGroup(svePopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(svePopupLayout.createSequentialGroup()
                                                .addGap(107, 107, 107)
                                                .addComponent(sve_close, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(svePopupLayout.createSequentialGroup()
                                                .addGap(81, 81, 81)
                                                .addGroup(svePopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(svePopupLayout.createSequentialGroup()
                                                                .addComponent(sales_label)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(sales_answer))
                                                        .addGroup(svePopupLayout.createSequentialGroup()
                                                                .addComponent(expendatures_label)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(expendatures_answer)))))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        svePopupLayout.setVerticalGroup(
                svePopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(svePopupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(sve_title)
                                .addGap(62, 62, 62)
                                .addGroup(svePopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(sales_label)
                                        .addComponent(sales_answer))
                                .addGap(53, 53, 53)
                                .addGroup(svePopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(expendatures_answer)
                                        .addComponent(expendatures_label))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                                .addComponent(sve_close)
                                .addGap(59, 59, 59))
        );


        //create checkout popup
        checkoutPopup.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        checkoutPopup.setAlwaysOnTop(true);
        checkoutPopup.setMinimumSize(new java.awt.Dimension(443, 500));
        checkoutPopup.setResizable(false);
        checkout_order_number.setText("Order Number:");
        checkout_email.setText("Email:");
        checkout_phone_number.setText("Phone Number:");
        checkout_phone_number_answer.setToolTipText("xxx-xxx-xxxx");
        checkout_address.setText("Address:");
        checkout_creditcard.setText("Credit Card Number:");
        checkout_creditcard_answer.setToolTipText("16 digit number");
        checkout_title.setFont(new java.awt.Font("Roland", 0, 24)); // NOI18N
        checkout_title.setForeground(new java.awt.Color(255, 0, 51));
        checkout_title.setText("Checkout");
        checkout_button.setText("Checkout");
        checkout_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkout_buttonActionPerformed(evt);
            }
        });
        checkout_date.setText("Date:");
        checkout_price.setText("Price:");
        checkout_order_number_answer.setText("TEST");
        checkout_price_answer.setText("TEST");
        checkout_date_answer.setText("TEST");

        //format checkout popup to look nice
        javax.swing.GroupLayout checkoutPopupLayout = new javax.swing.GroupLayout(checkoutPopup.getContentPane());
        checkoutPopup.getContentPane().setLayout(checkoutPopupLayout);
        checkoutPopupLayout.setHorizontalGroup(
                checkoutPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(checkoutPopupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(checkoutPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, checkoutPopupLayout.createSequentialGroup()
                                                .addGap(114, 114, 114)
                                                .addComponent(checkout_title)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, checkoutPopupLayout.createSequentialGroup()
                                                .addGap(9, 9, 9)
                                                .addGroup(checkoutPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(checkoutPopupLayout.createSequentialGroup()
                                                                .addComponent(checkout_creditcard)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                                                                .addComponent(checkout_creditcard_answer, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(checkoutPopupLayout.createSequentialGroup()
                                                                .addGroup(checkoutPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(checkout_date)
                                                                        .addComponent(checkout_email)
                                                                        .addComponent(checkout_phone_number)
                                                                        .addComponent(checkout_price)
                                                                        .addComponent(checkout_order_number)
                                                                        .addComponent(checkout_address))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGroup(checkoutPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(checkout_order_number_answer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(checkout_email_answer)
                                                                        .addComponent(checkout_phone_number_answer)
                                                                        .addComponent(checkout_address_answer)
                                                                        .addComponent(checkout_price_answer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(checkout_date_answer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(checkoutPopupLayout.createSequentialGroup()
                                .addGap(123, 123, 123)
                                .addComponent(checkout_button)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        checkoutPopupLayout.setVerticalGroup(
                checkoutPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(checkoutPopupLayout.createSequentialGroup()
                                .addGroup(checkoutPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, checkoutPopupLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(checkout_order_number)
                                                .addGap(18, 18, 18))
                                        .addGroup(checkoutPopupLayout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addGroup(checkoutPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(checkout_order_number_answer)
                                                        .addComponent(checkout_title))
                                                .addGap(18, 18, 18)))
                                .addGroup(checkoutPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(checkout_price)
                                        .addComponent(checkout_price_answer))
                                .addGap(18, 18, 18)
                                .addGroup(checkoutPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(checkout_email_answer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(checkout_email))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(checkoutPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(checkout_phone_number_answer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(checkout_phone_number))
                                .addGap(18, 18, 18)
                                .addGroup(checkoutPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(checkout_address_answer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(checkout_address))
                                .addGap(18, 18, 18)
                                .addGroup(checkoutPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(checkout_date)
                                        .addComponent(checkout_date_answer))
                                .addGap(20, 20, 20)
                                .addGroup(checkoutPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(checkout_creditcard_answer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(checkout_creditcard))
                                .addGap(18, 18, 18)
                                .addComponent(checkout_button)
                                .addContainerGap(65, Short.MAX_VALUE))
        );

        //create elements of the main screen
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 204));

        //create bottom search panel
        SearchPanel.setBackground(new java.awt.Color(204, 255, 204));
        SearchPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        SearchPanel.setName("SearchPanel"); // NOI18N
        //create the search button
        Main_Search_Button.setBackground(new java.awt.Color(204, 255, 204));
        Main_Search_Button.setText("Search");
        Main_Search_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Main_Search_ButtonActionPerformed(evt);
            }
        });
        //create the isbn button elements
        ISBNButton.setBackground(new java.awt.Color(204, 255, 204));
        SearchGroup.add(ISBNButton);
        ISBNButton.setSelected(true);
        ISBNButton.setText("ISBN");

        //create the title button elements
        TitleButton.setBackground(new java.awt.Color(204, 255, 204));
        SearchGroup.add(TitleButton);
        TitleButton.setText("Title");
        //create the author button elements
        AuthorButton.setBackground(new java.awt.Color(204, 255, 204));
        SearchGroup.add(AuthorButton);
        AuthorButton.setText("Author");
        //create the genre button elements
        GenreButton.setBackground(new java.awt.Color(204, 255, 204));
        SearchGroup.add(GenreButton);
        GenreButton.setText("Genre");
        //create the search field
        SearchField.setToolTipText("ISBN/Name/Author/Genre/NumOfPages");
        //create the num of pages button
        NumofPagesButton.setBackground(new java.awt.Color(204, 255, 204));
        SearchGroup.add(NumofPagesButton);
        NumofPagesButton.setText("Num of Pages");

        //format the main screen (for the bottom search section)
        javax.swing.GroupLayout SearchPanelLayout = new javax.swing.GroupLayout(SearchPanel);
        SearchPanel.setLayout(SearchPanelLayout);
        SearchPanelLayout.setHorizontalGroup(
                SearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(SearchPanelLayout.createSequentialGroup()
                                .addGroup(SearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(SearchPanelLayout.createSequentialGroup()
                                                .addGap(34, 34, 34)
                                                .addComponent(SearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(32, 32, 32)
                                                .addComponent(Main_Search_Button))
                                        .addGroup(SearchPanelLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(ISBNButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(TitleButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(AuthorButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(GenreButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(NumofPagesButton)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        SearchPanelLayout.setVerticalGroup(
                SearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(SearchPanelLayout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(SearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Main_Search_Button)
                                        .addComponent(SearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(SearchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(TitleButton)
                                        .addComponent(AuthorButton)
                                        .addComponent(GenreButton)
                                        .addComponent(ISBNButton)
                                        .addComponent(NumofPagesButton))
                                .addContainerGap(11, Short.MAX_VALUE))
        );

        //add the top part of the main screen
        Add_ISBN_tf.setText("ISBN");
        Add_ISBN_tf.setToolTipText("ISBN");
        Add_ISBN_tf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Add_ISBN_tfKeyReleased(evt);
            }
        });
        Main_Add_to_Basket_Button.setText("Add to Basket");
        Main_Add_to_Basket_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Main_Add_to_Basket_ButtonActionPerformed(evt);
            }
        });
        jList1.setModel(model);
        jList1.setName(""); // NOI18N
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        List_of_Books.setViewportView(jList1);
        Book_Search_Title.setFont(new java.awt.Font("November", 0, 24)); // NOI18N
        Book_Search_Title.setForeground(new java.awt.Color(255, 51, 51));
        Book_Search_Title.setText("Book Search");
        Main_Checkout_Button.setText("Checkout");
        Main_Checkout_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Main_Checkout_ButtonActionPerformed(evt);
            }
        });
        Main_View_Orders_Button.setText("View Orders");
        Main_View_Orders_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Main_View_Orders_ButtonActionPerformed(evt);
            }
        });
        Main_View_Reports_Button.setText("View Reports");
        Main_View_Reports_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Main_View_Reports_ButtonActionPerformed(evt);
            }
        });
        Add_to_Basket_Count.setModel(spinnermodel);
        Main_Add_Book_Button.setText("Add Book");
        Main_Add_Book_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Main_Add_Book_ButtonActionPerformed(evt);
            }
        });
        Main_Delete_Book_Button.setText("Delete Book");
        Main_Delete_Book_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Main_Delete_Book_ButtonActionPerformed(evt);
            }
        });
        Main_Add_Publisher_Button.setText("Add Publisher");
        Main_Add_Publisher_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Main_Add_Publisher_ButtonActionPerformed(evt);
            }
        });
        isbn_title.setFont(new java.awt.Font("STXihei", 1, 11)); // NOI18N
        isbn_title.setText("ISBN");
        title_title.setFont(new java.awt.Font("STXihei", 1, 11)); // NOI18N
        title_title.setText("Title");
        view_basket_button.setText("View Basket");
        view_basket_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                view_basket_buttonActionPerformed(evt);
            }
        });

        //format the top half of the main screen to look nice
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(SearchPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addGap(25, 25, 25)
                                                        .addComponent(isbn_title)
                                                        .addGap(50, 50, 50)
                                                        .addComponent(title_title)
                                                        .addGap(56, 56, 56)
                                                        .addComponent(Add_to_Basket_Count, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(Add_ISBN_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                        .addContainerGap()
                                                        .addComponent(List_of_Books)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(132, 132, 132)
                                                .addComponent(Book_Search_Title)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(Main_Checkout_Button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(Main_Add_to_Basket_Button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(Main_View_Orders_Button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(Main_View_Reports_Button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(Main_Add_Book_Button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(Main_Delete_Book_Button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(Main_Add_Publisher_Button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(view_basket_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(25, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(Book_Search_Title)
                                        .addComponent(view_basket_button))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Add_ISBN_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Main_Add_to_Basket_Button)
                                        .addComponent(Add_to_Basket_Count, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(isbn_title)
                                        .addComponent(title_title))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(List_of_Books, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(Main_Checkout_Button)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(Main_View_Orders_Button)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(Main_View_Reports_Button)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(Main_Add_Book_Button)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(Main_Delete_Book_Button)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(Main_Add_Publisher_Button)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SearchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }

    //what happens when you click on add to basket button
    private void Main_Add_to_Basket_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Main_Add_to_Basket_ButtonActionPerformed
        //check to see if logged in. If not, force login
        if(logged_in == false){
            signInPopup.setVisible(true);
        }
        else{
            //if logged in get the value of the counter
            int currentValue = (int)spinnermodel.getValue();
            //if the counter is greater than zero, try and add the book to the basket with that count and reset the counter
            if(currentValue > 0) {
                addBasketSQL();
                spinnermodel = new SpinnerNumberModel(0,0,0,1);
                Add_to_Basket_Count.setModel(spinnermodel);
            }
        }
    }

    //what happens when you click on the checkout button
    private void Main_Checkout_ButtonActionPerformed(java.awt.event.ActionEvent evt) {
        //check to see if logged in. If not, force login
        if(logged_in == false){
            signInPopup.setVisible(true);
        }
        else{
            //check to see if there are books in the basket, if so checkout
            if(num_in_basket > 0) {
                checkoutPopup.setVisible(true);
                checkout();
            }
        }
    }

    //what happens when you click the search button
    private void Main_Search_ButtonActionPerformed(java.awt.event.ActionEvent evt) {
        //search the database for the book
        search();
    }

    //when clicking the add book button, show popup
    private void Main_Add_Book_ButtonActionPerformed(java.awt.event.ActionEvent evt) {
        addBookPopup.setVisible(true);
    }

    //when clicking the delete book button, show popup
    private void Main_Delete_Book_ButtonActionPerformed(java.awt.event.ActionEvent evt) {
        deleteBookPopup.setVisible(true);
    }

    //when clicking the add publisher button, show popup
    private void Main_Add_Publisher_ButtonActionPerformed(java.awt.event.ActionEvent evt) {
        addPubPopup.setVisible(true);
    }

    //when clicking the delete book button in the delete book popup
    private void Delete_Book_ButtonActionPerformed(java.awt.event.ActionEvent evt) {
        //make sure isbn text field isn't empty. if it isn't delete the book from the database and reset to allow for more books to be deleted
        if( !Delete_Book_ISBN_tf.getText().isEmpty())
            deleteBookSQL();
        if(deleted_book) {
            deleteBookPopup.dispose();
            //also re-query the database for the book to no longer show
            search();
            deleted_book = false;
        }
    }

    //when clicking the sign in button on the sign in popup page
    private void SignIn_popup_buttonActionPerformed(java.awt.event.ActionEvent evt) {
        //query the database for the customer
        String output = SignIn_ID_tf.getText();
        signInSql(output);
        if (logged_in == true) {
            signInPopup.setVisible(false);
            //once logged in if owner, show buttons that regular customers should not have access to
            if (owner == true) {
                Main_View_Reports_Button.setVisible(true);
                Main_Add_Book_Button.setVisible(true);
                Main_Add_Publisher_Button.setVisible(true);
                Main_Delete_Book_Button.setVisible(true);
                repaint();
            }
        }
    }

    //when clicking the register button on the sign in popup, show the register popup
    private void RegisterSignInButtonActionPerformed(java.awt.event.ActionEvent evt) {
        registerPopup.setVisible(true);
    }

    //hide the book info popup when trying to exit
    private void book_info_okay_buttonActionPerformed(java.awt.event.ActionEvent evt) {
        bookInfoPopup.setVisible(false);
    }

    //if double clicking on the list of books, show the book info popup and populate it
    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            //utalize the isbn in querying the database
            String selectedItem = (String) jList1.getSelectedValue();
            int iend = selectedItem.indexOf(" ");
            if (iend != -1) {
                String ISBN = selectedItem.substring(0 , iend);
                findIndividualBook(ISBN);
                bookInfoPopup.setVisible(true);
            }
        }
    }

    //when trying to register and clicking the register button on the register popup
    private void Register_popup_button1ActionPerformed(java.awt.event.ActionEvent evt) {
        //make sure all text fields are filled out, and then register the user in the database (conditional on if the user already exists or not)
        if( !Register_ID_tf1.getText().isEmpty() && !Register_Name_tf1.getText().isEmpty() && !Register_Address_tf1.getText().isEmpty() && !Register_phone_number_tf1.getText().isEmpty() && !Register_email_tf1.getText().isEmpty())
            registerSQL();
        //if registering worked, hide the register popup
        if(register == true) {
            registerPopup.dispose();
            register = false;
        }
    }

    //when clicking on the exit button in the order info popup, hide the popup
    private void order_info_buttonActionPerformed(java.awt.event.ActionEvent evt) {
        orderInfoPopup.setVisible(false);

    }

    //when clicking on the view orders button, make sure they are logged in, if not force login, if they are query the database
    private void Main_View_Orders_ButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if(logged_in == false){
            signInPopup.setVisible(true);
        }
        else{
            viewOrdersPopup.setVisible(true);
            viewOrdersSQL();
        }
    }

    //when clicking on the close button in the view basket popup, hide the popup
    private void basket_close_buttonActionPerformed(java.awt.event.ActionEvent evt) {
        viewBasketPopup.setVisible(false);
    }

    //when clicking on the view basket button on the main page
    private void view_basket_buttonActionPerformed(java.awt.event.ActionEvent evt) {
        //force login if not logged in already. If logged in query the database to populare the view basket popup
        if(!logged_in){
            signInPopup.setVisible(true);
        }
        else {
            viewBasketPopup.setVisible(true);
            viewBasketSQL();
        }
    }

    //when clicking the add button in the add book popup
    private void Add_Book_ButtonActionPerformed(java.awt.event.ActionEvent evt) {
        //make sure all text fields are filled out, and add the book
        if( !Add_Book_authorid_tf1.getText().isEmpty() && !Add_Book_publisherid_tf1.getText().isEmpty() && !Add_Book_price_tf1.getText().isEmpty() && !Add_Book_nop_tf1.getText().isEmpty() && !Add_Book_genre_tf1.getText().isEmpty()&& !Add_Book_title_tf1.getText().isEmpty()&& !Add_Book_ISBN_tf1.getText().isEmpty())
            addBookSQL();
        if(added_book) {
            addBookPopup.dispose();
            //update the book list to show new added book
            search();
            added_book = false;
        }
    }

    //when clicking the add publisher button in the add publisher popup
    private void add_pub_buttonActionPerformed(java.awt.event.ActionEvent evt) {
        //make sure all text fields are filled out and insert the new publisher
        if( !Pub_ban_tf.getText().isEmpty() && !Pub_email_tf.getText().isEmpty() && !Pub_address_tf.getText().isEmpty() && !Pub_ID_tf.getText().isEmpty() && !Pub_name_tf.getText().isEmpty())
            addPublisherSQL();
        if(added_publisher) {
            addPubPopup.dispose();
            added_publisher = false;
        }
    }

    //when putting in an ISBN to add a book to your basket
    private void Add_ISBN_tfKeyReleased(java.awt.event.KeyEvent evt) {
        //if they are still getting rid of the current isbn fill set the counter to have a 1000 limit
        if(Add_ISBN_tf.getText().equals("ISBN") || Add_ISBN_tf.getText().equals("ISB") || Add_ISBN_tf.getText().equals("IS") || Add_ISBN_tf.getText().equals("I")){
            int currentValue = (int)spinnermodel.getValue();
            spinnermodel = new SpinnerNumberModel(currentValue,0,1000,1);
            Add_to_Basket_Count.setModel(spinnermodel);
        }
        else{
            //once digits are put into ISBN text field, check to see if the ISBN exists and find the number of books with that ISBN in stock
            checkcount();
            //force the maximum of the count to be the number of books in stock
            int currentValue = (int)spinnermodel.getValue();
            if (currentValue > max_count){
                currentValue = max_count;
            }
            spinnermodel = new SpinnerNumberModel(currentValue,0,max_count,1);
            Add_to_Basket_Count.setModel(spinnermodel);
        }
        repaint();
    }

    //if closing the view reports popup, hide the popup
    private void view_report_close_buttonActionPerformed(java.awt.event.ActionEvent evt) {
        viewReportsPopup.setVisible(false);
    }

    //when trying to see the sales per genre report, show and populate the popup
    private void spg_buttonActionPerformed(java.awt.event.ActionEvent evt) {
        spgSQL();
        spgPopup.setVisible(true);

    }

    //when trying to see the sales per author report, show and populate the popup
    private void spa_buttonActionPerformed(java.awt.event.ActionEvent evt) {
        spaSQL();
        spaPopup.setVisible(true);
    }

    //when trying to see the sales vs expendatures report, show and populate the popup
    private void sve_buttonActionPerformed(java.awt.event.ActionEvent evt) {
        sveSQL();
        svePopup.setVisible(true);
    }

    //when trying to close the sales per genre report, hide the popup
    private void spg_closeActionPerformed(java.awt.event.ActionEvent evt) {
        spgPopup.setVisible(false);
    }

    //when trying to close the sales per author report, hide the popup
    private void spa_closeActionPerformed(java.awt.event.ActionEvent evt) {
        spaPopup.setVisible(false);
    }

    //when trying to close the sales per publisher report, hide the popup
    private void spp_closeActionPerformed(java.awt.event.ActionEvent evt) {
        sppPopup.setVisible(false);
    }

    //when trying to close the sales vs expendatures report, hide the popup
    private void sve_closeActionPerformed(java.awt.event.ActionEvent evt) {
        svePopup.setVisible(false);
    }

    //when trying to see all reports, show the view reports popup
    private void Main_View_Reports_ButtonActionPerformed(java.awt.event.ActionEvent evt) {
        viewReportsPopup.setVisible(true);
    }

    //when trying to see the sales per publisher report, show and populate the popup
    private void spp_buttonActionPerformed(java.awt.event.ActionEvent evt) {
        sppSQL();
        sppPopup.setVisible(true);
    }

    //when trying to see the details of each book in your basket
    private void basket_listMouseClicked(java.awt.event.MouseEvent evt) {
        //once book is double clicked, use the isbn to query the database to get the information on the book
        if (evt.getClickCount() == 2) {
            String selectedItem = (String) basket_list.getSelectedValue();
            int iend = selectedItem.indexOf(" ");
            if (iend != -1) {
                String ISBN = selectedItem.substring(0 , iend);
                findIndividualBook(ISBN);
                bookInfoPopup.setVisible(true);
            }
        }
    }

    //when trying to checkout your basket and puchase the books
    private void checkout_buttonActionPerformed(java.awt.event.ActionEvent evt) {
        //make sure that all textfields are filled out, then checkout using the database
        if( !checkout_email_answer.getText().isEmpty() && !checkout_phone_number_answer.getText().isEmpty() && !checkout_address_answer.getText().isEmpty() && !checkout_creditcard_answer.getText().isEmpty())
            checkoutSQL();
        //once checkedout, reset and hide the popup
        if(checkout_worked) {
            checkoutPopup.dispose();
            checkout_worked = false;
        }
    }

    //when trying to see info on each previous order
    private void orders_listMouseClicked(java.awt.event.MouseEvent evt) {
        //double click on the order you want more detail on then uses order_number to query the database
        if (evt.getClickCount() == 2) {
            String Order_num;
            String selectedItem = (String) orders_list.getSelectedValue();
            int iend = selectedItem.indexOf(" ");
            if (iend != -1)
            {
                Order_num = selectedItem.substring(0 , iend);
                findIndividualOrder(Order_num);
                orderInfoPopup.setVisible(true);
            }

        }
    }

    //when closing the view orders popup, hide the popup
    private void orders_close_buttonActionPerformed(java.awt.event.ActionEvent evt) {
        viewOrdersPopup.setVisible(false);
    }





    // Variables declarations (lots and lots of variable declorations
    private javax.swing.JButton Add_Book_Button;
    private javax.swing.JLabel Add_Book_ISBN1;
    private javax.swing.JTextField Add_Book_ISBN_tf1;
    private javax.swing.JLabel Add_Book_authorid1;
    private javax.swing.JTextField Add_Book_authorid_tf1;
    private javax.swing.JLabel Add_Book_count1;
    private javax.swing.JTextField Add_Book_count_tf1;
    private javax.swing.JLabel Add_Book_genre1;
    private javax.swing.JTextField Add_Book_genre_tf1;
    private javax.swing.JLabel Add_Book_nop1;
    private javax.swing.JTextField Add_Book_nop_tf1;
    private javax.swing.JLabel Add_Book_percentage;
    private javax.swing.JTextField Add_Book_percentage_tf1;
    private javax.swing.JLabel Add_Book_price1;
    private javax.swing.JTextField Add_Book_price_tf1;
    private javax.swing.JLabel Add_Book_publisherid1;
    private javax.swing.JTextField Add_Book_publisherid_tf1;
    private javax.swing.JLabel Add_Book_title1;
    private javax.swing.JTextField Add_Book_title_tf1;
    private javax.swing.JLabel Add_Book_warehouseid1;
    private javax.swing.JTextField Add_Book_warehouseid_tf1;
    private javax.swing.JTextField Add_ISBN_tf;
    private javax.swing.JSpinner Add_to_Basket_Count;
    private javax.swing.JRadioButton AuthorButton;
    private javax.swing.JLabel Book_Search_Title;
    private javax.swing.JButton Delete_Book_Button;
    private javax.swing.JLabel Delete_Book_ISBN;
    private javax.swing.JTextField Delete_Book_ISBN_tf;
    private javax.swing.JLabel Delete_book_title;
    private javax.swing.JRadioButton GenreButton;
    private javax.swing.JRadioButton ISBNButton;
    private javax.swing.JLabel InfoOnRegister;
    private javax.swing.JScrollPane List_of_Books;
    private javax.swing.JButton Main_Add_Book_Button;
    private javax.swing.JButton Main_Add_Publisher_Button;
    private javax.swing.JButton Main_Add_to_Basket_Button;
    private javax.swing.JButton Main_Checkout_Button;
    private javax.swing.JButton Main_Delete_Book_Button;
    private javax.swing.JButton Main_Search_Button;
    private javax.swing.JButton Main_View_Orders_Button;
    private javax.swing.JButton Main_View_Reports_Button;
    private javax.swing.JRadioButton NumofPagesButton;
    private javax.swing.JLabel Pub_ID;
    private javax.swing.JTextField Pub_ID_tf;
    private javax.swing.JLabel Pub_address;
    private javax.swing.JTextField Pub_address_tf;
    private javax.swing.JLabel Pub_ban;
    private javax.swing.JTextField Pub_ban_tf;
    private javax.swing.JLabel Pub_email;
    private javax.swing.JTextField Pub_email_tf;
    private javax.swing.JLabel Pub_name;
    private javax.swing.JTextField Pub_name_tf;
    private javax.swing.JLabel Pub_phone;
    private javax.swing.JTextField Pub_phone_tf;
    private javax.swing.JButton RegisterSignInButton;
    private javax.swing.JTextField Register_Address_tf1;
    private javax.swing.JLabel Register_ID1;
    private javax.swing.JTextField Register_ID_tf1;
    private javax.swing.JTextField Register_Name_tf1;
    private javax.swing.JLabel Register_address1;
    private javax.swing.JLabel Register_email1;
    private javax.swing.JTextField Register_email_tf1;
    private javax.swing.JLabel Register_name1;
    private javax.swing.JLabel Register_phone_number1;
    private javax.swing.JTextField Register_phone_number_tf1;
    private javax.swing.JButton Register_popup_button1;
    private javax.swing.JTextField SearchField;
    private javax.swing.ButtonGroup SearchGroup;
    private javax.swing.JPanel SearchPanel;
    private javax.swing.JLabel SignIn_ID;
    private javax.swing.JTextField SignIn_ID_tf;
    private javax.swing.JButton SignIn_popup_button;
    private javax.swing.JLabel SignIn_title;
    private javax.swing.JRadioButton TitleButton;
    private javax.swing.JDialog addBookPopup;
    private javax.swing.JDialog addPubPopup;
    private javax.swing.JLabel add_book_title1;
    private javax.swing.JButton add_pub_button;
    private javax.swing.JLabel add_pub_title;
    private javax.swing.JLabel author_label_a;
    private javax.swing.JButton basket_close_button;
    private javax.swing.JList<String> basket_list;
    private javax.swing.JScrollPane basket_scroll_pane;
    private javax.swing.JDialog bookInfoPopup;
    private javax.swing.JButton book_info_okay_button;
    private javax.swing.JLabel book_info_title;
    private javax.swing.JLabel book_info_title1;
    private javax.swing.JDialog checkoutPopup;
    private javax.swing.JLabel checkout_address;
    private javax.swing.JTextField checkout_address_answer;
    private javax.swing.JButton checkout_button;
    private javax.swing.JLabel checkout_creditcard;
    private javax.swing.JTextField checkout_creditcard_answer;
    private javax.swing.JLabel checkout_date;
    private javax.swing.JLabel checkout_date_answer;
    private javax.swing.JLabel checkout_email;
    private javax.swing.JTextField checkout_email_answer;
    private javax.swing.JLabel checkout_order_number;
    private javax.swing.JLabel checkout_order_number_answer;
    private javax.swing.JLabel checkout_phone_number;
    private javax.swing.JTextField checkout_phone_number_answer;
    private javax.swing.JLabel checkout_price;
    private javax.swing.JLabel checkout_price_answer;
    private javax.swing.JLabel checkout_title;
    private javax.swing.JLabel count_label;
    private javax.swing.JLabel count_label_a;
    private javax.swing.JLabel count_label_p;
    private javax.swing.JDialog deleteBookPopup;
    private javax.swing.JLabel expendatures_answer;
    private javax.swing.JLabel expendatures_label;
    private javax.swing.JLabel genre_label;
    private javax.swing.JLabel info_ISBN;
    private javax.swing.JLabel info_author;
    private javax.swing.JLabel info_author_answer;
    private javax.swing.JLabel info_genre;
    private javax.swing.JLabel info_genre_answer;
    private javax.swing.JLabel info_isbn_answer;
    private javax.swing.JLabel info_number_of_pages;
    private javax.swing.JLabel info_number_of_pages_answer;
    private javax.swing.JLabel info_price;
    private javax.swing.JLabel info_price_answer;
    private javax.swing.JLabel info_publisher;
    private javax.swing.JLabel info_publisher_answer;
    private javax.swing.JLabel info_title;
    private javax.swing.JLabel info_title_answer;
    private javax.swing.JLabel isbn_title;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JList<String> jList1;
    private javax.swing.JDialog orderInfoPopup;
    private javax.swing.JLabel order_info_add;
    private javax.swing.JLabel order_info_add_answer;
    private javax.swing.JButton order_info_button;
    private javax.swing.JLabel order_info_ccn;
    private javax.swing.JLabel order_info_ccn_answer;
    private javax.swing.JLabel order_info_ds;
    private javax.swing.JLabel order_info_ds_answer;
    private javax.swing.JLabel order_info_email;
    private javax.swing.JLabel order_info_email_answer;
    private javax.swing.JLabel order_info_isbn;
    private javax.swing.JLabel order_info_isbn_answer;
    private javax.swing.JLabel order_info_number;
    private javax.swing.JLabel order_info_number_answer;
    private javax.swing.JLabel order_info_pd;
    private javax.swing.JLabel order_info_pd_answer;
    private javax.swing.JLabel order_info_phone;
    private javax.swing.JLabel order_info_phone_answer;
    private javax.swing.JLabel order_info_price;
    private javax.swing.JLabel order_info_price_answer;
    private javax.swing.JButton orders_close_button;
    private javax.swing.JList<String> orders_list;
    private javax.swing.JScrollPane orders_scroll_pane;
    private javax.swing.JLabel price_label;
    private javax.swing.JLabel price_label_a;
    private javax.swing.JLabel price_label_p;
    private javax.swing.JLabel publisher_label_p;
    private javax.swing.JDialog registerPopup;
    private javax.swing.JLabel register_title1;
    private javax.swing.JLabel sales_answer;
    private javax.swing.JLabel sales_label;
    private javax.swing.JDialog signInPopup;
    private javax.swing.JDialog spaPopup;
    private javax.swing.JButton spa_button;
    private javax.swing.JButton spa_close;
    private javax.swing.JList<String> spa_list;
    private javax.swing.JScrollPane spa_scroll;
    private javax.swing.JLabel spa_title;
    private javax.swing.JDialog spgPopup;
    private javax.swing.JButton spg_button;
    private javax.swing.JButton spg_close;
    private javax.swing.JList<String> spg_list;
    private javax.swing.JScrollPane spg_scroll;
    private javax.swing.JLabel spg_title;
    private javax.swing.JDialog sppPopup;
    private javax.swing.JButton spp_button;
    private javax.swing.JButton spp_close;
    private javax.swing.JList<String> spp_list;
    private javax.swing.JScrollPane spp_scroll;
    private javax.swing.JLabel spp_title;
    private javax.swing.JDialog svePopup;
    private javax.swing.JButton sve_button;
    private javax.swing.JButton sve_close;
    private javax.swing.JLabel sve_title;
    private javax.swing.JLabel title_title;
    private javax.swing.JDialog viewBasketPopup;
    private javax.swing.JDialog viewOrdersPopup;
    private javax.swing.JDialog viewReportsPopup;
    private javax.swing.JButton view_basket_button;
    private javax.swing.JLabel view_basket_title;
    private javax.swing.JLabel view_orders_title;
    private javax.swing.JButton view_report_close_button;
    private javax.swing.JLabel view_report_title;
    final DefaultListModel model = new DefaultListModel();
    final DefaultListModel spamodel = new DefaultListModel();
    final DefaultListModel sppmodel = new DefaultListModel();
    final DefaultListModel spgmodel = new DefaultListModel();
    final DefaultListModel basketmodel = new DefaultListModel();
    final DefaultListModel ordersmodel = new DefaultListModel();
    SpinnerModel spinnermodel = new SpinnerNumberModel(0,0,100,1);
    private boolean logged_in = false;
    private boolean owner = false;
    private String customer_number = null;
    private boolean register = false;
    private boolean added_book = false;
    private boolean added_publisher = false;
    private boolean deleted_book = false;
    private boolean checkout_worked = false;
    private int max_count = 1000;
    private int basket_num = 100;
    private int order_num = 100;
    private int num_in_basket = 0;

    //getting current date and the appropriate formats
    Date date = new Date(System.currentTimeMillis());
    SimpleDateFormat fformatter = new SimpleDateFormat("yy/MM/dd");
    String fs = fformatter.format(date);
    SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
    String s = formatter.format(date);






    //sql queries used to find all books that match the search where input param is the sql query
    private void findbook(String sql) {
        Statement stmt = null;
        //reset the list of books
        model.removeAllElements();
        // auto close connection
        //Create a connection to your postgresql database
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "qwer1234")) {
            stmt = conn.createStatement();
            //execute the query
            ResultSet rs = stmt.executeQuery(sql);
            //extract data from result
            while(rs.next()){
                //get the isbn and the title of each buck and add it to the list
                String ISBN = rs.getString("ISBN");
                String Title = rs.getString("title");
                String Book = ISBN + "    " + Title;
                model.addElement(Book);
            }
            //ensure each book is visible
            jList1.ensureIndexIsVisible(model.getSize());
            //close the query
            rs.close();
            //Catch all of the exceptions
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //sql query used at program launch to show all books at the start
    private void bookstart() {
        Statement stmt = null;
        //clear the list of books
        model.removeAllElements();
        // auto close connection
        //Create a connection to your postgresql database
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "qwer1234")) {
            stmt = conn.createStatement();

            //sql query to get the isbn and title from all the books that still exist in the bookstore
            String sql = "select isbn, title from books where visible = 'TRUE';";
            //execute the query
            ResultSet rs = stmt.executeQuery(sql);
            //extract data from result
            while(rs.next()){
                //update the list of books to include all available books
                String ISBN = rs.getString("ISBN");
                String Title = rs.getString("title");
                String Book = ISBN + "    " + Title + "    ";
                model.addElement(Book);
            }
            //make sure the list is visible
            jList1.ensureIndexIsVisible(model.getSize());
            //close the query
            rs.close();
            //Catch all of the exceptions
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //sql query to find information on specific book where number is the isbn of the book
    private void findIndividualBook(String number) {
        Statement stmt = null;
        // auto close connection
        //Create a connection to your postgresql database
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "qwer1234")) {
            stmt = conn.createStatement();
            //execute the query to find specific aspects of a specific book
            ResultSet rs = stmt.executeQuery("select isbn, title, genre, num_of_pages, p_name, name, price from books NATURAL JOIN writes as o LEFT JOIN authors ON o.author_id = authors.author_id Join publishers on books.publisher_id = publishers.publisher_id where isbn = '" + number +"' and visible = 'TRUE';");
            //extract data from result
            //for muliple authors, start with just adding the name and then comma seperating them
            int i = 0;
            while(rs.next()){
                if (i == 0){
                    info_author_answer.setText(rs.getString("name"));
                }
                else{
                    info_author_answer.setText(info_author_answer.getText() + ", " + rs.getString("name"));

                }
                //fill in all text fields with the appropriate information
                info_isbn_answer.setText(rs.getString("isbn"));
                info_title_answer.setText(rs.getString("title"));
                info_genre_answer.setText(rs.getString("genre"));
                info_number_of_pages_answer.setText(rs.getString("num_of_pages"));
                info_publisher_answer.setText(rs.getString("p_name"));
                info_price_answer.setText("$" + rs.getString("price"));
                i++;
            }
            //close the query
            rs.close();
            //Catch all of the exceptions
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //sql query used to sign in the the bookstore where the ID is the customer_id
    private void signInSql(String ID) {
        Statement stmt = null;
        // auto close connection
        //Create a connection to your postgresql database
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "qwer1234")) {
            stmt = conn.createStatement();
            //execute the query to find whether the customer exists already and if they are an owner or not
            ResultSet rs = stmt.executeQuery("select customer_id, owner from customer where customer_id = '" + ID + "';");
            //extract data from result
            while(rs.next()){
                //if they are an owner set the boolean
                if (rs.getString("owner").equals("t")) {
                    owner = true;
                }
                //if the customer exists, remember the ID and allow them to access the bookstore features
                customer_number = ID;
                logged_in = true;
            }
            //close the query
            rs.close();
            //Catch all of the exceptions
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //sql query to register customers in the database
    private void registerSQL() {
        Statement stmt = null;
        // auto close connection
        //Create a connection to your postgresql database
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "qwer1234")) {
            stmt = conn.createStatement();
            //get the text from the input fields
            String reg_id = Register_ID_tf1.getText();
            String reg_name = Register_Name_tf1.getText();
            String reg_add = Register_Address_tf1.getText();
            String reg_phone = Register_phone_number_tf1.getText();
            String reg_email = Register_email_tf1.getText();
            //execute the query and check to see if the customer exists already
            ResultSet check = stmt.executeQuery("select customer_id from customer where customer_id = '"+reg_id+"';");
            if(!check.next()){
                //if the customer does not exist, create the customer and basket for that customer
                register = true;
                stmt.executeUpdate("insert into customer values ('"+reg_id+"', '"+reg_name + "', '"+reg_add+"', '"+ reg_phone+"', '"+reg_email+"', 'FALSE');");
                //get the next basket number
                ResultSet check2 = stmt.executeQuery("select max(basket_id) from basket;");
                if(check2.next()) {
                    basket_num = Integer.parseInt(check2.getString(1))+1;
                }
                //create the basket
                stmt.executeUpdate("insert into basket values ('" + String.valueOf(basket_num) + "', '" + reg_id + "', 'TRUE');");

            }
            check.close();
            //Catch all of the exceptions
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //sql query to add a book to the database
    private void addBookSQL() {
        Statement stmt = null;
        // auto close connection
        //Create a connection to your postgresql database
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "qwer1234")) {
            stmt = conn.createStatement();
            //get input from the text fields
            String add_isbn = Add_Book_ISBN_tf1.getText();
            String add_title = Add_Book_title_tf1.getText();
            String add_genre = Add_Book_genre_tf1.getText();
            String add_nop = Add_Book_nop_tf1.getText();
            String add_price = Add_Book_price_tf1.getText();
            String add_authors = Add_Book_authorid_tf1.getText();
            //split muliple authors
            String[] authors = add_authors.split("\\s*,\\s*");
            String add_publisher = Add_Book_publisherid_tf1.getText();
            String add_count = Add_Book_count_tf1.getText();
            String add_warehouse = Add_Book_warehouseid_tf1.getText();
            String add_percentage = Add_Book_percentage_tf1.getText();
            //execute the query
            //check to see if the book exists, if not create the book
            ResultSet check = stmt.executeQuery("select isbn from books where isbn = '"+add_isbn+"';");
            if(!check.next()){
                added_book = true;
                stmt.executeUpdate("insert into books values ('"+add_isbn+"', '"+add_title + "', '"+add_genre+"', '"+ add_nop+"', '"+add_price+ "', '" + add_publisher + "', '" + add_count + "', '" + add_warehouse + "', '" + add_percentage + "', 'True');");
                //for each author create who writes what books to connect the author to the book
                for (String author : authors) {
                    stmt.executeUpdate("insert into writes values ('" + author + "', '" + add_isbn + "');");
                }
            }
            check.close();
            //Catch all of the exceptions
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //sql query to add publishers to the database
    private void addPublisherSQL() {
        Statement stmt = null;
        // auto close connection
        //Create a connection to your postgresql database
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "qwer1234")) {
            stmt = conn.createStatement();
            //get input from text fields
            String add_id = Pub_ID_tf.getText();
            String add_pname = Pub_name_tf.getText();
            String add_add = Pub_address_tf.getText();
            String add_email = Pub_email_tf.getText();
            String add_phone = Pub_phone_tf.getText();
            String add_bank = Pub_ban_tf.getText();

            //execute the query to find if the publisher already exists or not
            ResultSet check = stmt.executeQuery("select publisher_id from publishers where publisher_id = '"+add_id+"';");
            if(!check.next()){
                //if the publisher does not exist, add the publisher
                added_publisher = true;
                stmt.executeUpdate("insert into publishers values ('"+add_id+"', '"+add_pname + "', '"+add_add+"', '"+ add_email+"',  '"+add_phone + "', '"+add_bank+ "');");
            }
            check.close();
            //Catch all of the exceptions
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //sql query to delete a book from the database (rather than actually deleting the book, we just check to see if it is visible or not (therefore keeps orders and baskets appropriate)
    private void deleteBookSQL() {
        Statement stmt = null;
        // auto close connection
        //Create a connection to your postgresql database
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "qwer1234")) {

            //get the isbn from the text field
            String isbn = Delete_Book_ISBN_tf.getText();
            //execute the query
            //check to see if the book exists in the database
            ResultSet check = stmt.executeQuery("select isbn from books where isbn = '"+isbn+"' and visible = 'TRUE';");
            if(check.next()){
                //if it does then set it so that it is not visible
                deleted_book = true;
                stmt.executeUpdate("update books set visible = 'FALSE' where isbn = '" + isbn+"';");
            }
            check.close();
            //Catch all of the exceptions
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //sql query to get the number of a specific isbn in the bookstore
    private void checkcount() {
        Statement stmt = null;
        // auto close connection
        //Create a connection to your postgresql database
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "qwer1234")) {
            stmt = conn.createStatement();
            //get the isbn of the book
            String isbn = Add_ISBN_tf.getText();
            //execute the query
            //check to make sure that the book exists
            ResultSet check = stmt.executeQuery("select isbn, count from books where isbn = '" + isbn + "' and visible = 'TRUE';");
            if(check.next()){
                //if it does, set its count to be the max count available to buy
                max_count = check.getInt("count");
            }
            else{
                //if it doesn't exist, do not limit the user
                max_count = 1000;
            }
            //if the book is in the current basket of the user
            ResultSet rs = stmt.executeQuery("select basket_id from basket where customer_id = '" + customer_number + "' and current = 'TRUE';");
            if(rs.next()){
                //get the count from the isbn in the current basket
                ResultSet check2 = stmt.executeQuery("select * from contains where isbn = '" + isbn + "' and basket_id = '" + rs.getString("basket_id")+"';");
                if(check2.next()){
                    //do not allow the user to add more than the number of books available-the number in their basket
                    max_count = (max_count - Integer.parseInt(check2.getString("count")));
                }
            }
            check.close();
            //Catch all of the exceptions
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //this function searches for specific books (using the radio buttons and the search text field)
    private void search(){
        String output = SearchField.getText();
        String sql = null;
        //if there is nothing in the search field, query all books
        if(output.equals("")|| output.equals("ISBN/Name/Author/Genre/NumOfPages")){
            bookstart();
        }
        else {
            //depending on what radio button is selected, query using that element and the text in the text field
            if(ISBNButton.isSelected()){
                sql = "select isbn, title from books where ISBN = '" + SearchField.getText() + "' and visible = 'TRUE';";
            } else if(TitleButton.isSelected()) {
                sql = "select isbn, title from books where title = '" + SearchField.getText() + "'";
            }  else if(AuthorButton.isSelected()) {
                sql = "select isbn, title from books NATURAL JOIN writes as o LEFT JOIN authors ON o.author_id = authors.author_id where name = '" + SearchField.getText() + "' and visible = 'TRUE';";
            } else if(GenreButton.isSelected()){
                sql = "select isbn, title from books where genre = '" + SearchField.getText() + "' and visible = 'TRUE';";
            } else if(NumofPagesButton.isSelected()){
                sql = "select isbn, title from books where num_of_pages = '" + SearchField.getText() + "' and visible = 'TRUE';";
            }
            //query the books
            findbook(sql);
        }
    }

    //add books to basket sql queries
    private void addBasketSQL() {
        Statement stmt = null;
        // auto close connection
        //Create a connection to your postgresql database
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "qwer1234")) {
            stmt = conn.createStatement();
            //get the isbn and the count of the book you are trying ot add to the basket
            String isbn = Add_ISBN_tf.getText();
            int count = (int) spinnermodel.getValue();
            //execute the query
            //geet the basket number for the users current basket
            ResultSet check = stmt.executeQuery("select basket_id from basket where customer_id = '" + customer_number + "' and current = 'TRUE';");
            if(check.next()) {
                String basket = check.getString("basket_id");
                //make sure the book that you are trying to add exists
                ResultSet check2 = stmt.executeQuery("select isbn, visible from books where isbn = '" + isbn + "' and visible = 'TRUE';");
                if(check2.next()) {
                    //if the book and the basket exist, check to make sure the book is not already in the basket
                    ResultSet check3 = stmt.executeQuery("select isbn, basket_id, count from contains where isbn = '" + isbn + "' and basket_id = '" + basket + "';");
                    if(check3.next()){
                        //if the book is already in the basket, just update the count
                        stmt.executeUpdate("update contains set count = '"+ String.valueOf(Integer.parseInt(check3.getString("count")) + count) + "'where basket_id = '"+ basket + "' and isbn = '" + isbn + "';");
                    }
                    else {
                        //if the book is not already in the basket, add it to the basket
                        stmt.executeUpdate("insert into contains values ('" + basket + "', '" + isbn + "', '" + String.valueOf(count) + "');");
                        num_in_basket++;
                    }
                }
                check2.close();
            }
            check.close();
            //Catch all of the exceptions
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //sql queries to see all books currently in your basket
    private void viewBasketSQL() {
        num_in_basket = 0;
        Statement stmt = null;
        //remove elements from the basket model
        basketmodel.removeAllElements();
        // auto close connection
        //Create a connection to your postgresql database
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "qwer1234")) {
            stmt = conn.createStatement();
            //query to make sure that the customer and basket exist
            ResultSet check = stmt.executeQuery("select basket_id from basket where customer_id = '" + customer_number + "' and current = 'TRUE';");
            if(check.next()) {
                //if it does, get the important infromation about each book in the basket from the database
                String sql = "select books.isbn, title, contains.count from books right join contains on books.isbn = contains.isbn where visible = 'TRUE' and basket_id = '" + check.getString("basket_id") + "';";
                //execute the query
                ResultSet rs = stmt.executeQuery(sql);
                //extract data from result
                while (rs.next()) {
                    String ISBN = rs.getString("ISBN");
                    String Title = rs.getString("title");
                    String Count = rs.getString("count");
                    String Book = ISBN + "                    " + Count + "                      " + Title;
                    //add the books to the list
                    basketmodel.addElement(Book);
                    num_in_basket++;
                }
                //make sure all books are visible
                basket_list.ensureIndexIsVisible(basketmodel.getSize());
                //close the query
                rs.close();
            }
            check.close();
            //Catch all of the exceptions
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //view all sql previous orders made by the customer
    private void viewOrdersSQL() {
        Statement stmt = null;
        Statement stmt2 = null;
        //remove elements from the list to reset the list
        ordersmodel.removeAllElements();
        // auto close connection
        //Create a connection to your postgresql database
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "qwer1234")) {
            stmt = conn.createStatement();
            stmt2= conn.createStatement();
            //SQL query for the prerequisites of the course
            //get all old baskets (orders) from the customer
            ResultSet check = stmt.executeQuery("select basket_id from basket where customer_id = '" + customer_number + "' and current = 'FALSE';");
            while(check.next()) {
                //get the important info on all old orders
                String sql = "select basket_id, order_number, purchase_date, delivery_status from orders where basket_id = '" + check.getString("basket_id") + "';";
                //execute the query
                ResultSet rs = stmt2.executeQuery(sql);
                //extract data from result
                if(rs.next()) {
                    String Order_Number = rs.getString("order_number");
                    String Purchase_Date = rs.getString("purchase_date").substring(0,2) + "/" + rs.getString("purchase_date").substring(2,4) + "/" + rs.getString("purchase_date").substring(4,6);
                    String Delivery_Status = rs.getString("delivery_status");
                    String Order = Order_Number + "                        " + Purchase_Date + "                         " + Delivery_Status;
                    //add all previous orders to the list
                    ordersmodel.addElement(Order);
                }
                //make sure all elements in list are visible
                orders_list.ensureIndexIsVisible(ordersmodel.getSize());
                //close the query
                rs.close();
            }
            check.close();
            //Catch all of the exceptions
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //when trying to find info on specific order, query the database given a specific order number
    private void findIndividualOrder(String order_num) {
        Statement stmt = null;
        float price = 0;
        // auto close connection
        //Create a connection to your postgresql database
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "qwer1234")) {
            stmt = conn.createStatement();
            //execute the query to find important information on specific order number
            ResultSet rs = stmt.executeQuery("select order_number, purchase_date, delivery_status, o_address, o_email, o_phone, credit_card_number, contains.isbn, price, contains.count from orders Natural JOIN basket as o RIGHT JOIN contains on o.basket_id = contains.basket_id LEFT JOIN books on contains.isbn = books.isbn where order_number = '" + order_num +"';");

            //extract data from result and pretty print
            int i = 0;
            while(rs.next()){
                if (i == 0){
                    order_info_isbn_answer.setText(rs.getString("isbn"));
                }
                else{
                    order_info_isbn_answer.setText(order_info_isbn_answer.getText() + ", " + rs.getString("isbn"));

                }
                order_info_number_answer.setText(rs.getString("order_number"));
                order_info_pd_answer.setText( rs.getString("purchase_date").substring(0,2)+ "/" + rs.getString("purchase_date").substring(2,4)+ "/"+rs.getString("purchase_date").substring(4,6));
                order_info_ds_answer.setText(rs.getString("delivery_status"));
                order_info_add_answer.setText(rs.getString("o_address"));
                order_info_email_answer.setText(rs.getString("o_email"));
                order_info_phone_answer.setText(rs.getString("o_phone"));
                order_info_ccn_answer.setText("xxxx xxxx xxxx " + rs.getString("credit_card_number").substring(rs.getString("credit_card_number").length() - 4));
                price = price + (Float.parseFloat(rs.getString("price"))* Integer.parseInt(rs.getString("count")));
                i++;
            }
            order_info_price_answer.setText("$" + String.valueOf(price));
            //close the query
            rs.close();
            //Catch all of the exceptions
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //when trying to purchase all elements in basket query the database
    private void checkout() {
        Statement stmt = null;
        float price = 0;
        // auto close connection
        //Create a connection to your postgresql database
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "qwer1234")) {
            stmt = conn.createStatement();
            //execute the query to get the next order_num
            ResultSet check2 = stmt.executeQuery("select max(order_number) from orders;");
            if(check2.next()) {
                order_num = Integer.parseInt(check2.getString(1))+1;
            }
            //query to make sure that the basket exists
            ResultSet rs = stmt.executeQuery("select basket_id from basket where customer_id = '" + customer_number + "' and current = 'TRUE'");
            if(rs.next()){
                //get the important info on that specific basket
                rs = stmt.executeQuery("select price, contains.count from basket natural join contains left join books on contains.isbn = books.isbn where basket_id = '" + rs.getString("basket_id")+"';");
                while(rs.next()){
                    //total the price of all books in the basket
                    price = price + (Float.parseFloat(rs.getString("price"))* Integer.parseInt(rs.getString("count")));
                }
            }
            //print the results
            checkout_order_number_answer.setText(String.valueOf(order_num));
            checkout_price_answer.setText("$" + String.valueOf(price));
            checkout_date_answer.setText(fs);
            //close the query
            rs.close();
            //Catch all of the exceptions
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //when you have chosen to buy the books, query the database
    private void checkoutSQL() {
        Statement stmt = null;
        // auto close connection
        //Create a connection to your postgresql database
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "qwer1234")) {
            stmt = conn.createStatement();
            //get important info from the text fields about the order you are trying to place
            String order = String.valueOf(order_num);
            String address = checkout_address_answer.getText();
            String email = checkout_email_answer.getText();
            String phone = checkout_phone_number_answer.getText();
            String creditcard = checkout_creditcard_answer.getText();
            String date = s;

            //execute the  to make sure the basket exists
            ResultSet check = stmt.executeQuery("select basket_id from basket where customer_id = '" + customer_number + "' and current = 'TRUE';");
            if(check.next()) {
                String basket = check.getString("basket_id");
                //execute the query to create the order
                stmt.executeUpdate("insert into orders values ('" + order + "', '" + basket + "', 'N/A', '"+address + "', '"+email+"', '"+phone+"', '"+creditcard+"', '"+date+"');");
                num_in_basket = 0;
                //execute the query to get the important info on the basket and the num of books that were bought
                ResultSet check2 = stmt.executeQuery("select books.count, contains.count, books.isbn from basket natural join contains left join books on contains.isbn = books.isbn where basket_id = '" + basket + "';");
                while(check2.next()) {
                    //take the count of books in the bookstore and subtract the amount bought
                    int total = Integer.parseInt(check2.getString(1));
                    int bc = Integer.parseInt(check2.getString(2));
                    int newtotal = total-bc;
                    //update the number of books that are left in the bookstore
                    stmt.executeUpdate("update books set count = '"+String.valueOf(newtotal)+"' where isbn = '"+ check2.getString("isbn")+"';");
                    //*****THIS IS WHERE THE EMAIL WOULD BE SENT TO THE PUBLISHER*****
                    //if the total number of books left is less than 10, order the amount of books that were sold a month prior to todays date
                    if(newtotal < 10){
                        stmt.executeUpdate("update books set count = sub_q.sum_val from (select sum(books.count) + sum(contains.count) as sum_val, purchase_date, books.isbn from orders natural join basket left join contains on basket.basket_id = contains.basket_id left join books on contains.isbn = books.isbn where books.count < 10 and purchase_date between '" + String.valueOf(Integer.parseInt(s)-1001) + "' and '"+ s +"' group by books.isbn, purchase_date) as sub_q where sub_q.isbn = books.isbn");
                    }
                    //make the basket of the order no longer your current basket as it has been ordered
                    stmt.executeUpdate("update basket set current = 'FALSE' where basket_id = '"+basket+"';");
                    //get a new basket for that customer
                    ResultSet check3 = stmt.executeQuery("select max(basket_id) from basket;");
                    if(check3.next()) {
                        basket_num = Integer.parseInt(check3.getString(1))+1;
                    }
                    //create the new basket
                    stmt.executeUpdate("insert into basket values ('" + String.valueOf(basket_num) + "', '" + customer_number + "', 'TRUE');");
                    checkout_worked = true;
                    check3.close();
                }
                check2.close();
            }
            check.close();
            //Catch all of the exceptions
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //get all information on the sales per genre in the database
    private void spgSQL() {
        Statement stmt = null;
        //reset the list
        spgmodel.removeAllElements();
        // auto close connection
        //Create a connection to your postgresql database
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "qwer1234")) {
            stmt = conn.createStatement();
            //query the important information grouping together the genres of books from all sold books
            ResultSet check = stmt.executeQuery("select sum(price), genre, sum(contains.count) from orders natural join basket left join contains on basket.basket_id = contains.basket_id left join books on contains.isbn = books.isbn group by genre");
            while(check.next()) {
                //extract data from result and pretty print
                String Amount = "$" + check.getString(1);
                String Genre = check.getString(2);
                String Count = check.getString(3);
                String Total = Count + "                " + Amount + "                     " + Genre;
                //add each genre to the list
                spgmodel.addElement(Total);
            }
            //make sure all elements in list is visible
            spg_list.ensureIndexIsVisible(spgmodel.getSize());
            //close the query
            check.close();
            //Catch all of the exceptions
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //get all information on the sales per author of the database
    private void spaSQL() {
        Statement stmt = null;
        //clear the list
        spamodel.removeAllElements();
        // auto close connection
        //Create a connection to your postgresql database
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "qwer1234")) {
            stmt = conn.createStatement();
            //query the important information grouping together the authors of books from all sold books
            ResultSet check = stmt.executeQuery("select sum(price), authors.author_id, name, sum(contains.count) from orders natural join basket left join contains on basket.basket_id = contains.basket_id left join books on contains.isbn = books.isbn left join writes on books.isbn = writes.isbn left join authors on writes.author_id = authors.author_id group by authors.author_id");
            while(check.next()) {
                //extract data from result and pretty print
                String Amount = "$" + check.getString(1);
                String Name = check.getString(3);
                String Count = check.getString(4);
                String Total = Count + "                 " + Amount + "             " + Name;
                //list all authors
                spamodel.addElement(Total);
            }
            //make sure all elements are visible
            spa_list.ensureIndexIsVisible(spamodel.getSize());
            //close the query
            check.close();
            //Catch all of the exceptions
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //get all information on the sales per publisher of the database
    private void sppSQL() {
        Statement stmt = null;
        //clear the list
        sppmodel.removeAllElements();
        // auto close connection
        //Create a connection to your postgresql database
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "qwer1234")) {
            stmt = conn.createStatement();
            //query the important information grouping together the publishers of books from all sold books
            ResultSet check = stmt.executeQuery("select sum(price), publishers.publisher_id, p_name, sum(contains.count) from orders natural join basket left join contains on basket.basket_id = contains.basket_id left join books on contains.isbn = books.isbn left join publishers on books.publisher_id = publishers.publisher_id group by publishers.publisher_id");
            while(check.next()) {
                //extract data from result and pretty print
                String Amount = "$" + check.getString(1);
                String Name = check.getString(3);
                String Count = check.getString(4);
                String Total = Count + "                " + Amount + "              " + Name;
                //add each publisher to the list
                sppmodel.addElement(Total);
                spp_list.ensureIndexIsVisible(sppmodel.getSize());

            }
            //make sure list is visible
            //close the query
            check.close();
            //Catch all of the exceptions
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //query for the sales vs expendatures of the bookstore (all time, where sales are total amount for each book, and expendatures are what was givin to the publisers)
    private void sveSQL() {
        Statement stmt = null;
        // auto close connection
        //Create a connection to your postgresql database
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "qwer1234")) {
            stmt = conn.createStatement();
            //query for the total amount sold of all books as well as the amount given to the publishers (percentage of each book sold)
            ResultSet check = stmt.executeQuery("select sum(t.cost), sum(t.expendatures) as total from (select sum(price*contains.count) as cost, sum(percentage)*0.01*sum(price) as expendatures from orders natural join basket left join contains on basket.basket_id = contains.basket_id left join books on contains.isbn = books.isbn group by books.isbn) as t;");
            while(check.next()) {
                //extract data from result
                sales_answer.setText("$"+check.getString(1));
                expendatures_answer.setText("$"+String.format("%.2f ", Float.parseFloat(check.getString(2))));
            }
            //close the query
            check.close();
            //Catch all of the exceptions
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    //main function to run program
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;}}
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProjectUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProjectUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProjectUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProjectUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //create the form and display it
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProjectUI().setVisible(true);
            }
        });
    }

}

