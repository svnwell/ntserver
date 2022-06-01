import time
import socket

HOST = '127.0.0.1'
PORT = 10010

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.connect((HOST, PORT))

    while True:
        s.sendall(time.strftime('%Y/%m/%d %H:%M:%S').encode())
        rsp = s.recv(1024)

        print(f"Received: {rsp!r}")

        time.sleep(1)
