import psycopg2
from config import POSTGRES_CONN_STRING

db = psycopg2.connect(POSTGRES_CONN_STRING)