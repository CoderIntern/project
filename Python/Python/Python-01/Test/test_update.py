# coding=UTF-8
import MySQLdb
from _sqlite3 import sqlite_version, Row
conn = MySQLdb.connect(
                        host = '127.0.0.1',
                        port = 3306,
                        user = 'root',
                        passwd = '123456',
                        db = 'test',
                        charset = 'utf8'
                        )
cursor = conn.cursor()
sql_insert = " insert into user(name,age) values('de',24) "
sql_update = " update user set name = 'yangdejun' where id = 2 "
sql_delete = " delete from user where userid > 4 "
try:
    cursor.execute(sql_insert)
    print cursor.rowcount
    cursor.execute(sql_update)
    print cursor.rowcount
    cursor.execute(sql_delete)
    print cursor.rowcount
    conn.commit()
except Exception as e:
    print e
    conn.rollback()

cursor.close()
conn.close()