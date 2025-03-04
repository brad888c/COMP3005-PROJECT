/*
COMP3005 Project
Bradley Campbell
101070833
These are the tables used for the Bookstore Database
*/

--Warehouse table which identifies the warehouse a book can be in as well as the warehouses address and phone number
create table warehouse
	(warehouse_id			numeric(7,0),
	 address			varchar(30),
	 phone				varchar(12),
	 primary key (warehouse_id)
	);

--Publishers table which identifies different publishers with a unique id as well as important information on the publishers.
--Phone number is a text field as it allows for a dynamic amountof characters as a publisher can multiple phone numbers according to the problem statement
create table publishers
	(publisher_id			numeric(5,0), 
	 p_name				varchar(30),
	 address			varchar(30),
	 email				varchar(30),
	 t_phone			text,
	 bank_account			numeric(16,0),
	 primary key (publisher_id)
	);

--Books table which has a unique ISBN as all different books have unique ISBNs. I utalized judgement for sizing of numeric values such as price and number of pages.
--This table uses the foreign keys of warehouse_id to identify which warehouse the book is in, and publisher_id as each book can only have one publisher
--the visible attribute is if the book is purchasable from the store or not, and the count is how many of that book the store has
create table books
	(ISBN				numeric(7,0), 
	 title				varchar(20), 
	 genre      			varchar(15),
	 num_of_pages			numeric(4,0),
	 price				numeric(4,2),
	 publisher_id			numeric(5,0),
	 count				numeric(2,0),
	 warehouse_id			numeric(7,0),
	 percentage			numeric(2,0),
	 visible			boolean,
	 primary key (ISBN),
	 foreign key (publisher_id) references publishers,
	 foreign key (warehouse_id) references warehouse
	);

--Authors table which has primary key for author_id as authors may have the same name
create table authors
	(author_id			numeric(5,0), 
	 name				varchar(30), 
	 primary key (author_id)
	);

--Writes table which connects books and authors together. This is because multiple authors can write the same book.
--Therefore, both author_id and ISBN are the primary key
create table writes
	(author_id			numeric(5,0), 
	 ISBN				numeric(7,0), 
	 primary key (author_id, isbn),
	 foreign key (author_id) references authors,
	 foreign key (isbn) references books
	);

--Customer table which is for each unique customer. Includes an id as the primary key so that each customer can be identified (may have same name etc. it is essentailly a username)
--Includes attribute owner as the owner of the bookstore has some specific abilities that all other customers do not have
create table customer
	(customer_id			varchar(12), 
	 c_name				varchar(30), 
	 address			varchar(30),
	 phone				varchar(12),
	 email				varchar(30),
	 owner				boolean,
	 primary key (customer_id)
	);

--Basket table is each individual basket for each customer. Therefore, your basket_id is the primary key but the relation also connects to customer
--The attribute current is there to see whether it was a basket that has already been ordered or not
create table basket
	(basket_id			numeric(7,0),
	 customer_id			varchar(12),
	 current			boolean,
	 primary key (basket_id),
	 foreign key (customer_id) references customer
	);

--Orders table which is utalized when a customer checksout. Directly relates to basket which is why basket_id is a foreign key. 
--Users have the ability to pick different relations depending on whether or not they want to have the same info as they did when they signed up
create table orders
	(order_number			numeric(5,0),
	 basket_id			numeric(7,0),
	 delivery_status		varchar(12),
	 o_address			varchar(30),
	 o_email			varchar(30),
	 o_phone			varchar(12),
	 credit_card_number		numeric(16,0),
	 purchase_date			numeric(6,0),
	 primary key (order_number),
	 foreign key (basket_id) references basket
	);

--Contains table which is used to see which books are contained in which basket. This allows a basket to have multiple books at the same time
--Therefore it references both basket_id and ISBN. Count is used to see how many of each book are in the basket.
create table contains
	(basket_id			numeric(7,0),
	 ISBN				numeric(7,0),
	 count				numeric(2,0),
	 primary key(basket_id, ISBN),
	 foreign key (basket_id) references basket,
	 foreign key (ISBN) references books
	);