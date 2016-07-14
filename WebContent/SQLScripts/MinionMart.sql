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
pwd varchar2(50) not null,
pwdSecure varchar2(50),
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