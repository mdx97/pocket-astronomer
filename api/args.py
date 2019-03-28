class Args:
    def __init__(self, d={}):
        self.dictionary = d
    
    def __getitem__(self, key):
        if key not in self.dictionary:
            raise Exception("Error: missing argument '{}'.".format(key))
        return self.dictionary[key]
    
    def __setitem__(self, key, value):
        self.dictionary[key] = value