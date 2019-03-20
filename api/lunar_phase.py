import json

STAGES = [
    "New Moon",
    "Waxing Crescent",
    "First Quarter",
    "Waxing Gibbous",
    "Full Moon",
    "Waning Gibbous",
    "Last Quarter",
    "Waning Crescent"
]

class LunarPhase:
    def __init__(self, stage=0):
        self.stage = stage

    @property
    def name(self):
        return STAGES[self.stage]

    def to_dict(self):
        return { "name": self.name }

def get_lunar_phase():
    # TODO: This is just a placeholder method.
    return json.dumps(LunarPhase().to_dict())