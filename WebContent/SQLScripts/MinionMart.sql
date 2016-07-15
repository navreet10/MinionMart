drop table cart;
drop table wishlist;
drop table userorder;
drop table minionorder;
drop table product;
drop table minionuser;
drop table prodtype;


drop sequence minionuser_id_seq;
drop sequence prodtype_id_seq;
drop sequence cart_id_seq;
drop sequence wishlist_id_seq;
drop sequence minionorder_id_seq;
drop sequence product_id_seq;
drop sequence userorder_id_seq;

create sequence  minionuser_id_seq start with 1 increment by 1 nocache;
create sequence  prodtype_id_seq start with 1 increment by 1 nocache;
create sequence  cart_id_seq start with 1 increment by 1 nocache;
create sequence  wishlist_id_seq start with 1 increment by 1 nocache;
create sequence  minionorder_id_seq start with 1 increment by 1 nocache;
create sequence  product_id_seq start with 1 increment by 1 nocache;
create sequence  userorder_id_seq start with 1 increment by 1 nocache;

create table minionuser (
userId integer primary key,
username varchar2(50) unique not null,
pwd varchar2(200) not null,
pwdSecure varchar2(200),
useremail varchar2(100) not null,
useraddress varchar2(200) not null,
userzip varchar(10) not null
);



create table prodtype (
typeId integer primary key,
typename varchar2(50) not null,
typedesc varchar2(200) not null
);



create table product (
prodId integer primary key,
prodname varchar2(50) not null,
proddesc varchar2(200) not null,
produrl varchar2(100) not null,
prodprice number not null,
typeid integer not null,
CONSTRAINT fk_prod_typeid FOREIGN KEY (typeid) references prodtype(typeid)
);



create table minionorder (
orderid integer primary key,
ordername varchar2(100) not null,
prodId integer not null,
qtty number default 1 not null,
CONSTRAINT fk_order_prodid FOREIGN KEY (prodId) references product(prodId)
);


create table userorder (
userorderid  integer primary key,
userId integer unique not null,
ordercount integer default 0,
CONSTRAINT fk_user_userid FOREIGN KEY (userid) references minionuser(userId)
);



create table cart ( 
cartid integer primary key,
userId integer not null,
prodId integer not null,
qtty number default 1  not null,
active integer default 0,
CONSTRAINT fk_cart_prodId FOREIGN KEY (prodId) references product(prodId),
CONSTRAINT fk_cart_userId FOREIGN KEY (userid) references minionuser(userId)
);


create table wishlist ( 
wishid integer primary key,
userId integer not null,
prodId integer not null,
CONSTRAINT fk_wish_prodId FOREIGN KEY (prodId) references product(prodId),
CONSTRAINT fk_wish_userId FOREIGN KEY (userid) references minionuser(userId)
);



--Insertion for minionuser
Insert into minionuser (userId,username,pwd,pwdSecure,useremail,useraddress,userzip) values (minionuser_id_seq.nextval,'Admin','d3c0c099bb4b9ae5f831478f351771f1f7c2355c9e12f4ad063d6acf21237ab3','r6BedpL38UXGemj2etQSYzvxYH7KmBAv8/Fl9lcUSS8=','admin@gmail.com','2301 Street NW,Washington DC',20037); 
Insert into minionuser (userId,username,pwd,pwdSecure,useremail,useraddress,userzip) values (minionuser_id_seq.nextval,'Can','9abf616d1b24b2b27a398ca03ccf6e2f15c99155d51a99888b77af5da6cf1386','bThvDNH0L6ImIR0SQrqvQgI5WzBmttJcyo1JSx0qUP0=','can@gmail.com','24 Street NW,RockVille,MD',20804);
Insert into minionuser (userId,username,pwd,pwdSecure,useremail,useraddress,userzip) values (minionuser_id_seq.nextval,'dan','b9e48843592eae14619de26718c36b3f8c6b1f7638ac9a39d92e2b57136d70e9','OpYLbtIup1jtYl4JZ2ZvZVYXxz7AGPg/uFzerSxj/cM=','dan@gmail.com','23 Street NW,Washington DC',20037);
Insert into minionuser (userId,username,pwd,pwdSecure,useremail,useraddress,userzip) values (minionuser_id_seq.nextval,'van','36dcca83c2063c062591bb6e4f8c82ebce0b38ffddfe36b0a694e9895860ef7c','kdlO2qB6KXCb50SeiZXjNPqwVnG/R/z4A8nAgxDgJk0=','van@gmail.com','25 Street NW,Washington',20008);
Insert into minionuser (userId,username,pwd,pwdSecure,useremail,useraddress,userzip) values (minionuser_id_seq.nextval,'tan','c3d3ba4572d424dd11b90407fa0093ea6d9e63252cba2c52512564bb0f6c5bbb','bWu0ANGClELPl4noBEeYaA3SSTupPWrQG09aDz+xdgM=','tan@gmail.com','42 Street SW,Califorina',19080);
Insert into minionuser (userId,username,pwd,pwdSecure,useremail,useraddress,userzip) values (minionuser_id_seq.nextval,'pan','dbe7744910020c116583c7e907486e9a990f37baad23b8973aad61c05569e417','mp4Lph/10YMlFdWdYRV2wS+HGEv4Jm8zioBRJGgiztM=','pan@gmail.com','57 Street,Califorina',20080);

select * from minionuser;

--Insertion for prodtype;
Insert into prodtype (typeId,typename,typedesc) values (prodtype_id_seq.nextval,'Clothes','Look different everyday with collection of Jeans, Dresses');
Insert into prodtype (typeId,typename,typedesc) values (prodtype_id_seq.nextval,'Movies,Games','Fun awaiting for the weekend;Video games,Cards');
Insert into prodtype (typeId,typename,typedesc) values (prodtype_id_seq.nextval,'Books','Educational, Novel,and many more books');
Insert into prodtype (typeId,typename,typedesc) values (prodtype_id_seq.nextval,'Grocery','Snacks,MealEssentials,Beverages,Chocolates');
Insert into prodtype (typeId,typename,typedesc) values (prodtype_id_seq.nextval,'Electronics','Mobile,Computer,Gadgets');
Insert into prodtype (typeId,typename,typedesc) values (prodtype_id_seq.nextval,'Sports and Outdoor','Camping,Hiking');

select * from prodtype;

--insertion for product

Insert into product (prodId,prodname,proddesc,produrl,prodprice,TYPEID) values (product_id_seq.nextval,'Dress','Summer dress','images/dress',100,1);
Insert into product (prodId,prodname,proddesc,produrl,prodprice,TYPEID) values (product_id_seq.nextval,'Pant','Jeans shorts','images/pant',40,1);
Insert into product (prodId,prodname,proddesc,produrl,prodprice,TYPEID) values (product_id_seq.nextval,'Shirts','Cloth full slevees','images/shirts',55,1);

Insert into product (prodId,prodname,proddesc,produrl,prodprice,TYPEID) values (product_id_seq.nextval,'Poker','200 dices set','images/poker',50,2);
Insert into product (prodId,prodname,proddesc,produrl,prodprice,TYPEID) values (product_id_seq.nextval,'Chess','Magnetic board','images/chess',100,2);
Insert into product (prodId,prodname,proddesc,produrl,prodprice,TYPEID) values (product_id_seq.nextval,'Movies','Award winner movies','images/movies',50,2);

Insert into product (prodId,prodname,proddesc,produrl,prodprice,TYPEID) values (product_id_seq.nextval,'Text Books','Science, History, Maths','images/textbooks',120,3);
Insert into product (prodId,prodname,proddesc,produrl,prodprice,TYPEID) values (product_id_seq.nextval,'Puzzle books','Sudoku','images/puzzlebook',10,3);
Insert into product (prodId,prodname,proddesc,produrl,prodprice,TYPEID) values (product_id_seq.nextval,'Cooking books','Indian Cooking recipies','cookingbook',50,3);

Insert into product (prodId,prodname,proddesc,produrl,prodprice,TYPEID) values (product_id_seq.nextval,'Chips','Lays','images/chips',10,4);
Insert into product (prodId,prodname,proddesc,produrl,prodprice,TYPEID) values (product_id_seq.nextval,'Drinks','Beer, Wine','images/drinks',20,4);
Insert into product (prodId,prodname,proddesc,produrl,prodprice,TYPEID) values (product_id_seq.nextval,'Vitamins','Vitamin D','images/vitamins',30,4);

Insert into product (prodId,prodname,proddesc,produrl,prodprice,TYPEID) values (product_id_seq.nextval,'Iphone 6s','Latest Model','images/Iphone',750,5);
Insert into product (prodId,prodname,proddesc,produrl,prodprice,TYPEID) values (product_id_seq.nextval,'Dell Laptop','15 inc,Touch screen','images/laptop',1000,5);
Insert into product (prodId,prodname,proddesc,produrl,prodprice,TYPEID) values (product_id_seq.nextval,'TV','40 inch LED','images/TV',250,5);

Insert into product (prodId,prodname,proddesc,produrl,prodprice,TYPEID) values (product_id_seq.nextval,'Camping tent','3 person fast pinch cabin','images/tent',100,6);
Insert into product (prodId,prodname,proddesc,produrl,prodprice,TYPEID) values (product_id_seq.nextval,'Shoes','Hiking shoes','images/shoes',100,6);
Insert into product (prodId,prodname,proddesc,produrl,prodprice,TYPEID) values (product_id_seq.nextval,'Bike','Safe fuel! Differnt terrian bikes','images/bike',250,6);

select * from product;

--insertion for 









