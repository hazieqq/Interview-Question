
try:
    file = open("text.txt","r")
    arr=[]
    age=[]
    line = file.read()
    lines = line.strip()
    a = lines.splitlines()
    for i in range(len(a)):
        if a[i] != "":
            arr.append(a[i].replace(" ",""))
    for i in range(len(arr)):
        s = str(arr[i]).split('"')
        for j in range(len(s)):
            if j==0 or j==2:
                c = str(s[j]).split(',')
                if j==0:
                    for k in range(len(c)):
                        if k>=2:
                            if c[k].isdigit():
                                age.append(int(c[k]))
                else:
                    for k in range(len(c)):
                        if c[k].isdigit():
                            age.append(int(c[k]))
    print("Max age=",max(age))
                            
    file.close()
except FileNotFoundError:
    print("file not dound")