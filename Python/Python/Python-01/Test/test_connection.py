# coding=UTF-8
import MySQLdb
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
cursor.close()
conn.close()