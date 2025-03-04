/*
COMP3005 Project
Bradley Campbell
101070833
These are the inserts used for the Bookstore Database to populate the bookstore
*/

--creates one warehouse where all books are stored
insert into warehouse values ('1234567', '37 Main St. Ottawa ON', '613-666-6666');

--creates a bunch of publishers 
insert into publishers values ('10000','OReilly','2690  Richmond St. Oshawa ON','accounts@oreilly.com','694-226-9918, 737-189-2121','3115989846214543');
insert into publishers values ('10001','Penguin','150  Islington Ave. Toronto ON','info@penguinpub.com','377-619-2219','0113379844015989');
insert into publishers values ('10002','Prentice Hall','3837  Princess St. Kingston ON','ph@phall.com','645-997-8812, 682-995-1407','2276006546103447');
insert into publishers values ('10003','Random House','2064 Main St. Langenburg SK','accounting@rh.com','303-989-1199','6465158624583322');
insert into publishers values ('10004','Wiley','3355 2nd St. Winnipeg MB','wiley@wiley.com','289-344-1111','6119051348515884');

--creates a bunch of books which references the publishers aswell as the one warehouse. Also decides whether the book is currantly available in the bookstore or not
insert into books values ('9781200','Data Smart','science','235','16.99','10004','26','1234567','1','TRUE');
insert into books values ('9789584','Freakonomics','economics','197','20.99','10001','43','1234567','8','TRUE');
insert into books values ('9781025','Superfreakonomics','economics','179','13.99','10004','46','1234567','6','TRUE');
insert into books values ('9780154','Catch 22','fiction','178','31.99','10004','32','1234567','1','TRUE');
insert into books values ('9786377','The Trial','fiction','198','33.99','10003','46','1234567','9','FALSE');
insert into books values ('9785767','The Veteran','fiction','177','19.99','10004','44','1234567','4','TRUE');
insert into books values ('9784686','The Outsider','fiction','198','16.99','10002','42','1234567','1','TRUE');
insert into books values ('9782543','The Brethren','fiction','174','32.99','10000','11','1234567','3','TRUE');
insert into books values ('9786808','Jurassic Park','fiction','174','15.99','10003','22','1234567','4','TRUE');
insert into books values ('9787653','Sea of Poppies','fiction','197','22.99','10000','13','1234567','9','TRUE');
insert into books values ('9780804','Burning Bright','fiction','175','34.99','10000','18','1234567','1','TRUE');
insert into books values ('9787033','The City of Joy','fiction','177','23.99','10003','16','1234567','6','TRUE');
insert into books values ('9780773','In a Free State','fiction','196','9.99','10004','45','1234567','6','TRUE');
insert into books values ('9782921','The Moon is Down','fiction','196','10.99','10000','18','1234567','4','FALSE');
insert into books values ('9788209','False Impressions','fiction','177','17.99','10003','23','1234567','4','TRUE');
insert into books values ('9783037','A Farewell to Arms','fiction','179','32.99','10000','49','1234567','3','TRUE');
insert into books values ('9787498','Journal of a Novel','fiction','196','26.99','10002','47','1234567','1','TRUE');
insert into books values ('9788377','Orientalism','history','197','24.99','10003','48','1234567','1','TRUE');
insert into books values ('9783740','The Last Mughal','history','199','17.99','10004','22','1234567','10','TRUE');
insert into books values ('9783718','The Age of Wrath','history','238','35.99','10004','34','1234567','10','TRUE');
insert into books values ('9781767','Birth of a Theorem','science','234','25.99','10003','48','1234567','6','TRUE');
insert into books values ('9786787','Mein Kampf','nonfiction','212','22.99','10002','35','1234567','5','TRUE');
insert into books values ('9780657','Dylan on Dylan','nonfiction','197','15.99','10002','37','1234567','1','TRUE');
insert into books values ('9782365','Gun Gayin Awadi','nonfiction','212','9.99','10004','28','1234567','2','TRUE');
insert into books values ('9784838','The Last Lecture','nonfiction','197','27.99','10000','26','1234567','9','TRUE');
insert into books values ('9788561','A Russian Journal','nonfiction','196','25.99','10001','38','1234567','2','TRUE');
insert into books values ('9783879','Free Will','philosophy','203','11.99','10000','41','1234567','9','TRUE');
insert into books values ('9780545','On Education','philosophy','203','14.99','10004','25','1234567','8','TRUE');
insert into books values ('9784119','Electric Universe','science','201','21.99','10004','31','1234567','10','TRUE');
insert into books values ('9782317','The Tao of Physics','science','179','17.99','10003','34','1234567','9','TRUE');

--creates authors who will end up writing the books
insert into authors values ('34682','Sandra Brown');
insert into authors values ('37804','Dr. Seuss');
insert into authors values ('61751','JK Rowling');
insert into authors values ('66213','John Steinbeck');
insert into authors values ('15041','Ernest Hemingway');
insert into authors values ('22112','Stephen King');
insert into authors values ('79240','Agatha Christie');
insert into authors values ('95516','Dean Koontz');
insert into authors values ('71349','Beatrix Potter');
insert into authors values ('44991','Lewis Carroll');
insert into authors values ('69533','Harold Robbins');

--creates and describes which author(s) wrote which books (references both authors and books)
insert into writes values ('34682','9781200');
insert into writes values ('37804','9789584');
insert into writes values ('22112','9789584');
insert into writes values ('61751','9781025');
insert into writes values ('66213','9780154');
insert into writes values ('15041','9786377');
insert into writes values ('22112','9785767');
insert into writes values ('79240','9784686');
insert into writes values ('95516','9782543');
insert into writes values ('71349','9786808');
insert into writes values ('44991','9787653');
insert into writes values ('69533','9780804');
insert into writes values ('61751','9780804');
insert into writes values ('34682','9787033');
insert into writes values ('37804','9780773');
insert into writes values ('61751','9782921');
insert into writes values ('66213','9788209');
insert into writes values ('15041','9783037');
insert into writes values ('22112','9787498');
insert into writes values ('79240','9788377');
insert into writes values ('95516','9783740');
insert into writes values ('71349','9783718');
insert into writes values ('34682','9783718');
insert into writes values ('44991','9781767');
insert into writes values ('69533','9786787');
insert into writes values ('34682','9780657');
insert into writes values ('37804','9782365');
insert into writes values ('61751','9784838');
insert into writes values ('66213','9788561');
insert into writes values ('15041','9783879');
insert into writes values ('34682','9783879');
insert into writes values ('22112','9780545');
insert into writes values ('79240','9784119');
insert into writes values ('95516','9782317');

--creates current customers of the bookstore. Also includes 1 owner user for the bookstore.
insert into customer values ('123456789','Brad Campbell','Carleton U','613-000-0000','braddcampbell@carleton.ca','TRUE');
insert into customer values ('111111','Patrick Kane','4294 Lynden Rd. Creemore ON','778-391-5077','flaviog@yahoo.ca','FALSE');
insert into customer values ('222222','Avril Lavigne','804 9th Ave. Lethbridge AB','247-098-2852','klaudon@msn.com','FALSE');
insert into customer values ('333333','Lamar Jackson','598 Tycos Dr. Toronto ON','971-103-9291','nachbaur@yahoo.ca','FALSE');
insert into customer values ('444444','Kanye West','4523 rue Levy Montreal QC','412-978-6667','mhoffman@sbcglobal.net','FALSE');
insert into customer values ('555555','LeBron James','525 Main St. Turner Valley AB','406-389-5551','greear@live.com','FALSE');
insert into customer values ('666666','Michael B. Jordan','4703 MacLaren St. Ottawa ON','947-471-0372','gmcgath@att.net','FALSE');
insert into customer values ('777777','Nick Kyrgios','4902 rue St-Paul Ste Agathe QC','422-326-8414','ozawa@yahoo.ca','FALSE');
insert into customer values ('888888','Harry Kane','1943 Toy Ave. Oshawa ON','819-901-0709','nacho@live.com','FALSE');
insert into customer values ('999999','Sylvester Stallone','866 Lynden Rd. Moonstone ON','577-640-1295','skajan@aol.com','FALSE');

--creates baskets for the current customers. The false baskets are baskets that turned into orders while the true baskets are baskets that they currently have access to.
insert into basket values ('1000000','333333','FALSE');
insert into basket values ('1000001','888888','FALSE');
insert into basket values ('1000002','888888','FALSE');
insert into basket values ('1000003','888888','FALSE');
insert into basket values ('1000004','888888','FALSE');
insert into basket values ('1000005','444444','FALSE');
insert into basket values ('1000006','666666','FALSE');
insert into basket values ('1000007','444444','FALSE');
insert into basket values ('1000008','111111','FALSE');
insert into basket values ('1000009','111111','FALSE');
insert into basket values ('1000010','777777','FALSE');
insert into basket values ('1000011','444444','TRUE');
insert into basket values ('1000012','888888','FALSE');
insert into basket values ('1000013','111111','FALSE');
insert into basket values ('1000014','777777','TRUE');
insert into basket values ('1000015','333333','FALSE');
insert into basket values ('1000016','333333','FALSE');
insert into basket values ('1000017','888888','FALSE');
insert into basket values ('1000018','555555','FALSE');
insert into basket values ('1000019','999999','FALSE');
insert into basket values ('1000020','666666','FALSE');
insert into basket values ('1000021','555555','FALSE');
insert into basket values ('1000022','111111','FALSE');
insert into basket values ('1000023','666666','TRUE');
insert into basket values ('1000024','888888','FALSE');
insert into basket values ('1000025','111111','FALSE');
insert into basket values ('1000026','222222','FALSE');
insert into basket values ('1000027','111111','FALSE');
insert into basket values ('1000028','111111','TRUE');
insert into basket values ('1000029','222222','TRUE');
insert into basket values ('1000030','333333','TRUE');
insert into basket values ('1000031','888888','TRUE');
insert into basket values ('1000032','555555','FALSE');
insert into basket values ('1000033','555555','TRUE');

--creates orders which reference previous baskets 
insert into orders values ('10001','1000000','N/A','4523 rue Levy Montreal QC','mhoffman@sbcglobal.net','412-978-6667','3677354982430780','200120');
insert into orders values ('10002','1000001','N/A','1943 Toy Ave. Oshawa ON','nacho@live.com','819-901-0709','4229049252613190','200131');
insert into orders values ('10003','1000002','N/A','866 Lynden Rd. Moonstone ON','skajan@aol.com','577-640-1295','7409340651992530','200212');
insert into orders values ('10004','1000004','N/A','4703 MacLaren St. Ottawa ON','gmcgath@att.net','947-471-0372','4435354675648080','200307');
insert into orders values ('10005','1000005','N/A','"1795 King St. Oshawa ON"','nacho@live.com','819-901-0709','4229049252613190','200309');
insert into orders values ('10006','1000006','N/A','4294 Lynden Rd. Creemore ON','flaviog@yahoo.ca','778-391-5077','4291501711158920','200313');
insert into orders values ('10007','1000007','N/A','4902 rue St-Paul Ste Agathe QC','ozawa@yahoo.ca','422-326-8414','3691049902606450','200313');
insert into orders values ('10008','1000008','N/A','598 Tycos Dr. Toronto ON','nachbaur@yahoo.ca','971-103-9291','7105801127117300','200321');
insert into orders values ('10009','1000010','N/A','804 9th Ave. Lethbridge AB','klaudon@msn.com','247-098-2852','3456841265490500','200326');
insert into orders values ('10010','1000011','N/A','525 Main St. Turner Valley AB','greear@live.com','792-614-9926','9664132995390700','200327');
insert into orders values ('10011','1000013','N/A','598 Tycos Dr. Toronto ON','nachbaur@yahoo.ca','971-103-9291','7105801127117300','200402');
insert into orders values ('10012','1000014','N/A','598 Tycos Dr. Toronto ON','nachbaur@yahoo.ca','971-103-9291','7105535564815030','200406');
insert into orders values ('10013','1000018','N/A','4294 Lynden Rd. Creemore ON','flaviog@yahoo.ca','778-391-5077','4291501711158920','200406');

--creates contains which describes which books are in which baskets and how many. This can be for both current baskets or past orders. References both basket and books
insert into contains values ('1000000','9782317','1');
insert into contains values ('1000001','9782543','1');
insert into contains values ('1000001','9781025','3');
insert into contains values ('1000002','9783879','1');
insert into contains values ('1000004','9787498','2');
insert into contains values ('1000004','9783740','2');
insert into contains values ('1000004','9784119','1');
insert into contains values ('1000005','9786787','1');
insert into contains values ('1000005','9784838','1');
insert into contains values ('1000005','9788561','2');
insert into contains values ('1000006','9784119','3');
insert into contains values ('1000006','9781767','1');
insert into contains values ('1000006','9787033','1');
insert into contains values ('1000007','9787498','2');
insert into contains values ('1000008','9780657','1');
insert into contains values ('1000010','9784686','3');
insert into contains values ('1000010','9784838','1');
insert into contains values ('1000011','9780545','1');
insert into contains values ('1000011','9788209','1');
insert into contains values ('1000013','9780154','1');
insert into contains values ('1000014','9784686','2');
insert into contains values ('1000014','9782317','1');
insert into contains values ('1000018','9782317','2');



