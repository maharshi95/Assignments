--1
select date(time_created) as order_date, SUM(sell_price * quantity) as sales
FROM Orders INNER JOIN OrderDetails
WHERE Orders.order_id = OrderDetails.order_id
and Orders.order_status != 'Cancelled' 
GROUP BY order_date
Order by order_date

--2
select date(time_created) as order_date, SUM((sell_price  - buy_price)* quantity) as sales
FROM Orders INNER JOIN OrderDetails
WHERE Orders.order_id = OrderDetails.order_id
and Orders.order_status != 'Cancelled' 
GROUP BY order_date
Order by order_date

--3
select Customer.customer_id, company_name, AVG(quantity) as average_order_size
FROM Orders 
INNER JOIN OrderDetails
	on Orders.order_id = OrderDetails.order_id
INNER JOIN Customer 
	on Customer.customer_id = Orders.customer_id
GROUP BY Customer.customer_id

--4
update Product set deleted = true where product_id = ?
