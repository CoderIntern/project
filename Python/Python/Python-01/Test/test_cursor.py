# coding=UTF-8
import MySQLdb
from _sqlite3 import sqlite_version
conn = MySQLdb.connect(
                        host = '127.0.0.1',
                        port = 3306,
                        user = 'root',
                        passwd = '123456',
                        db = 'test',
                        charset = 'utf8'
                        )
cursor = conn.cursor()
print conn
print cursor
sql = 'select * from user'
cursor.execute(sql)
rs = cursor.fetchone()
print rs
rs = cursor.fetchmany(3)
print rs
rs = cursor.fetchall()
print rs
cursor.close()
conn.close()