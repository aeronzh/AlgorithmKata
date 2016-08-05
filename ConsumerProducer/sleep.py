from threading import Thread, Lock
import time
import random

queue = []
lock = Lock()

class ProducerThread(Thread):
    def run(self):
        nums = range(5) # will create list {0, 1, 2, 3, 4}
        global queue
        while True:
            num = random.choice(nums) # select a random number from nums
            lock.acquire()
            queue.append(num)
            print "Produced",num
            lock.release()
            time.sleep(random.random() * 2) # control produce speed. produce slowly

class ConsumerThread(Thread):
    def run(self):
        global queue
        while True:
            while not queue:
                print "Nothing in queue, but consumer will try to consume"
                time.sleep(random.random()) # might waste time waiting for producer
                continue
            lock.acquire()
            num = queue.pop(0)
            print "Consumed", num
            lock.release()
            time.sleep(random.random()) # control consume speed 

ProducerThread().start()
ProducerThread().start()
ConsumerThread().start()