from redis import StrictRedis

r = StrictRedis(decode_responses=True)

for key in r.keys():
    if key.startswith('spring:session'):
        r.delete(key)