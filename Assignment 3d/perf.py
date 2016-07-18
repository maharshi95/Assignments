import MySQLdb
import random
import datetime,time

fmt = '%m/%d/%Y %I:%M %p'

sql_product_id = """SELECT product_id FROM Product"""
sql_order_id = """SELECT order_id FROM Orders"""
sql_customer_id = """SELECT customer_id FROM Customer"""
sql_order_insert = "insert into Orders(`customer_id`,`time_created`, `order_status`,`payment_mode`,`payment_status`) VALUES (%s, %s, %s, %s, %s);"


size = 5
payment_modes = ['COD','Credit Card', 'Debit Card', 'Net Banking', 'E Wallet']
order_status = ['CREATED','PLACED','SHIPPED','DELIVERED','CANCELLED']
pay_status = ['DONE','PENDING']


def get_ids_from_db(query):
    db = MySQLdb.connect("127.0.0.1", "root", "toor", "testdb")
    cursor = db.cursor()
    cursor.execute(query)
    l = [int(id[0]) for id in cursor.fetchall()]
    db.close()
    return l

def strTimeProp(start, end, prop):
    """Get a time at a proportion of a range of two formatted times.

    start and end should be strings specifying times formated in the
    given format (strftime-style), giving an interval [start, end].
    prop specifies how a proportion of the interval to be taken after
    start.  The returned time will be in the specified format.
    """

    stime = time.mktime(time.strptime(start, fmt))
    etime = time.mktime(time.strptime(end, fmt))

    ptime = stime + prop * (etime - stime)

    return datetime.datetime.fromtimestamp(ptime)


def randomDate(start, end, prop):
    return strTimeProp(start, end, prop)


def random_date():
    return randomDate("1/1/2008 1:30 PM", "1/1/2016 4:50 AM", random.random())

product_ids = get_ids_from_db(sql_product_id)
user_ids = get_ids_from_db(sql_customer_id)
order_ids = get_ids_from_db(sql_order_id)

print len(order_ids)


def gen_order_data():
    order_data = []

    for i in range(1000000):
        order_entry = []

        index = random.randint(0,4)
        order_entry.append(user_ids[index])

        order_entry.append(random_date())

        index = random.randint(0,4)
        order_entry.append(order_status[index])

        index = random.randint(0, 4)
        order_entry.append(payment_modes[index])

        index = random.randint(0,1)
        order_entry.append(pay_status[index])

        order_data.append(order_entry)

    return order_data


def gen_order_item_data():
    order_item_data = []
    for order_id in order_ids:
        n_items = random.randint(1, 4);

        for i in range(n_items):

            index = random.randint(0, 4)
            product_id = product_ids[index];

            quantity = random.randint(1,10)

            sell_price = random.randint(100,1000)

            item_entry = [order_id,product_id,quantity,sell_price]

            order_item_data.append(item_entry)
    return order_item_data



def populate_db_orders():
    print "generating order data"
    order_data = gen_order_data()
    print str(len(order_data)) + " orders created, pushing into database"
    db = MySQLdb.connect("localhost", "root", "toor", "testdb")
    print "Connection established"
    cursor = db.cursor()
    print "Starting to batch insert"
    cursor.executemany(sql_order_insert, order_data)
    print "batch insert complete"
    db.commit()
    print "committing to db"
    db.close()
    print 'closing connection'

