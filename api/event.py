import json
from database import db
from utils import date_to_string, datetime_to_string
from datetime import datetime, timedelta

class Event:
    def __init__(self, id, name, datetime, classification):
        self.id = id
        self.name = name
        self.datetime = datetime
        self.classification = classification
    
    def serialize(self):
        return {
            "id": self.id,
            "name": self.name,
            "datetime": datetime_to_string(self.datetime),
            "classification": self.classification
        }

def get_events(start_date, end_date, classification):
    query = "SELECT id, name, date, classification FROM events WHERE date >= %s AND date <= %s AND classification=%s"
    cursor = db.cursor()
    cursor.execute(query, (date_to_string(start_date), date_to_string(end_date), classification))
    results = cursor.fetchall()
    data = []

    for result in results:
        dt = result[2]
        event = Event(result[0], result[1], dt, result[3])
        data.append(event.serialize())
    
    return json.dumps(data)