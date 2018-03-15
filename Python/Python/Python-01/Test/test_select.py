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
print conn
print cursor
sql = 'select * from user'
cursor.execute(sql)
rs = cursor.fetchall()
for row in rs:
    print 'userid=%d username=%s, userage=%d' % row
cursor.close()
conn.close()