/*
COMP3005 Project
Bradley Campbell
101070833
These are the inserts used for the Bookstore Database to populate the bookstore
*/
/***************
ALL OF THESE QUERIES HAVE JAVA CODE MIXED IN AS THEY ARE DYNAMIC TO THE INPUT FROM THE BOOKSTORE
***************/


//SELECTS

//Query used to get the total price of all the sales per genre 
"select sum(price), genre, sum(contains.count) from orders natural join basket left join contains on basket.basket_id = contains.basket_id left join books on contains.isbn = books.isbn group by genre"

//Query used to get the total price of all the sales per author
"select sum(price), authors.author_id, name, sum(contains.count) from orders natural join basket left join contains on basket.basket_id = contains.basket_id left join books on contains.isbn = books.isbn left join writes on books.isbn = writes.isbn left join authors on writes.author_id = authors.author_id group by authors.author_id"

//Query used to get the total price of all the sales per publisher
"select sum(price), publishers.publisher_id, p_name, sum(contains.count) from orders natural join basket left join contains on basket.basket_id = contains.basket_id left join books on contains.isbn = books.isbn left join publishers on books.publisher_id = publishers.publisher_id group by publishers.publisher_id"

//Query used to get the total cost vs expendatures (lots of math done to get the correct percentage per book times the number of books as each book has a different percentage given to the publisher)
"select sum(t.cost), sum(t.expendatures) as total from (select sum(price*contains.count) as cost, sum(percentage)*0.01*sum(price) as expendatures from orders natural join basket left join contains on basket.basket_id = contains.basket_id left join books on contains.isbn = books.isbn group by books.isbn) as t;"

//Query used to seach Bookstore for all books available that have a specific ISBN
"select isbn, title from books where ISBN = '" + SearchField.getText() + "' and visible = 'TRUE'";

//Query used to seach Bookstore for all books available that have a specific title
"select isbn, title from books where title = '" + SearchField.getText() + "' and visible = 'TRUE';"

//Query used to seach Bookstore for all books available that have a specific author
"select isbn, title from books NATURAL JOIN writes as o LEFT JOIN authors ON o.author_id = authors.author_id where name = '" + SearchField.getText() + "' and visible = 'TRUE';"

//Query used to seach Bookstore for all books available that have a specific genre
"select isbn, title from books where genre = '" + SearchField.getText() + "' and visible = 'TRUE';"

//Query used to seach Bookstore for all books available that have a specific num_of_pages
"select isbn, title from books where num_of_pages = '" + SearchField.getText() + "' and visible = 'TRUE';"

//Query used to seach Bookstore for all books available
"select isbn, title from books where visible = 'TRUE';"

//Query used to get specific info to desplay the user for a specific isbn 
"select isbn, title, genre, num_of_pages, p_name, name, price from books NATURAL JOIN writes as o LEFT JOIN authors ON o.author_id = authors.author_id Join publishers on books.publisher_id = publishers.publisher_id where isbn = '" + number +"' and visible = 'TRUE';"

//Query used to see whether the customer is an owner or not
"select customer_id, owner from customer where customer_id = '" + ID + "';"

//Query used to see whether the customer exists or not
"select customer_id from customer where customer_id = '"+reg_id+"';"

//Query to get the last basket id given (used for giving the next basket id to a user)
"select max(basket_id) from basket;"

//Query used for checking whether a book exists in the database (used when adding a new book)
"select isbn from books where isbn = '"+add_isbn+"';"

//Query used for checking whether a publisher exists in the database (used when adding a new publisher)
"select publisher_id from publishers where publisher_id = '"+add_id+"';"

//Query to see what the max number of one book the bookstore has (used when limiting the user trying to buy more books than the store has)
"select isbn, count from books where isbn = '" + isbn + "' and visible = 'TRUE';"

//Query to get the basket id of a specific customer
"select basket_id from basket where customer_id = '" + customer_number + "' and current = 'TRUE';"

//Query used to delete a book when owner try's to delete specific isbn (also used when adding a book)
"select isbn, visible from books where isbn = '" + isbn + "' and visible = 'TRUE';"

//Query to see if specific book already exists in the users current basket
"select isbn, basket_id, count from contains where isbn = '" + isbn + "' and basket_id = '" + basket + "';"

//Query to get information on books that are in a users current basket (used when clicking View Basket button)
"select books.isbn, title, contains.count from books right join contains on books.isbn = contains.isbn where visible = 'TRUE' and basket_id = '" + check.getString("basket_id") + "';"

//Query used to get all of the completed baskets of a particular user (get baskets of all the orders)
"select basket_id from basket where customer_id = '" + customer_number + "' and current = 'FALSE';"

//Query used to get specific information on a particular customers previous orders
"select basket_id, order_number, purchase_date, delivery_status from orders where basket_id = '" + check.getString("basket_id") + "';"

//Query used to get specific information on a particular order (including isbn's price, etc.) this query is then looped just like many others to accumulate important information
"select order_number, purchase_date, delivery_status, o_address, o_email, o_phone, credit_card_number, contains.isbn, price, contains.count from orders Natural JOIN basket as o RIGHT JOIN contains on o.basket_id = contains.basket_id LEFT JOIN books on contains.isbn = books.isbn where order_number = '" + order_num +"';"

//Query to get the largest basket number (used when creating a new order/order_number)
"select max(order_number) from orders;"

//Query used to get the basket id of the current basket of the customer when they are trying to checkout and order books
"select basket_id from basket where customer_id = '" + customer_number + "' and current = 'TRUE'");

//Query used to get important info on a specific basket when the user is checking out. The results of this are accumulated to get an overall order result (e.g., price gets totaled etc.)
"select price, contains.count from basket natural join contains left join books on contains.isbn = books.isbn where basket_id = '" + rs.getString("basket_id")+"';"

//Query used to get imporant information on the specific books and count of books that were just bought when user is checking out and creating an order
"select books.count, contains.count, books.isbn from basket natural join contains left join books on contains.isbn = books.isbn where basket_id = '" + basket + "';"



//UPDATES



//Updates when a purchase has made the number of a specific book in the store less than 10 in which it gets the number of that isbn that was purchased a month before and adds that to the count (as if the store automatically orders more books)
"update books set count = sub_q.sum_val from (select sum(books.count) + sum(contains.count) as sum_val, purchase_date, books.isbn from orders natural join basket left join contains on basket.basket_id = contains.basket_id left join books on contains.isbn = books.isbn where books.count < 10 and purchase_date between '" + String.valueOf(Integer.parseInt(s)-1001) + "' and '"+ s +"' group by books.isbn, purchase_date) as sub_q where sub_q.isbn = books.isbn;"

//Updates a specific book to not show when owner deletes it from the store
"update books set visible = 'FALSE' where isbn = '" + isbn+"';"

//Updates the count of a specific book in a basket when the customer adds the same book to a basket that already contains that specific book
"update contains set count = '"+ String.valueOf(Integer.parseInt(check3.getString("count")) + count) + "'where basket_id = '"+ basket + "' and isbn = '" + isbn + "';"

//Updates the number of books that are in the store of a specific isbn (occurs when someone buys that book, you have to take that number away from your store count)
"update books set count = '"+String.valueOf(newtotal)+"' where isbn = '"+ check2.getString("isbn")+"';"

//Updates when the basket turns into an order as the basket is no longer their active basket and they are given a new basket. Updates the current value to be false
"update basket set current = 'FALSE' where basket_id = '"+basket+"';"



//INSERTS


//Inserts a new customer with the provided values when they register for an account
"insert into customer values ('"+reg_id+"', '"+reg_name + "', '"+reg_add+"', '"+ reg_phone+"', '"+reg_email+"', 'FALSE');"

//Inserts a new basket when a customer registers (as they can now purchase books) or orders something and needs a new basket
"insert into basket values ('" + String.valueOf(basket_num) + "', '" + reg_id + "', 'TRUE');"

//Inserts a book with the provided information when owner tries to add a new book to the bookstore
"insert into books values ('"+add_isbn+"', '"+add_title + "', '"+add_genre+"', '"+ add_nop+"', '"+add_price+ "', '" + add_publisher + "', '" + add_count + "', '" + add_warehouse + "', '" + add_percentage + "', 'True');"

//Inserts into writes when the owner adds a new book as there needs to be a link between the author and the books
"insert into writes values ('" + author + "', '" + add_isbn + "');");

//Inserts a new publisher with the provided values when the owner adds a publisher to the bookstore
"insert into publishers values ('"+add_id+"', '"+add_pname + "', '"+add_add+"', '"+ add_email+"',  '"+add_phone + "', '"+add_bank+ "');"

//Inserts into contains when a user adds a book to their basket
"insert into contains values ('" + basket + "', '" + isbn + "', '" + String.valueOf(count) + "');"

//Inserts into orders when a user checks out and orders their current basket
"insert into orders values ('" + order + "', '" + basket + "', 'N/A', '"+address + "', '"+email+"', '"+phone+"', '"+creditcard+"', '"+date+"');"








