import psycopg2
import os
from urllib.parse import urlparse

url = urlparse(os.environ['DATABASE_URL'])
dbname = url.path[1:]
db = psycopg2.connect(dbname=dbname, user=url.username, password=url.password, host=url.hostname, port=url.port)