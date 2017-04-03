import os
import subprocess, sys
rootdir = '/home/prateek'
ext = ('.mp4','.avi')

#opening media by passing in the whole path of song works for both Windows and Linux
def open_file(filename):
    if sys.platform == "win64":
        os.startfile(filename)
    else:
        opener ="open" if sys.platform == "darwin" else "xdg-open"
        subprocess.call([opener, filename])

# where indexing of .mp4 and .avi files is done
with open(".index.txt", "w") as f:        
    for subdir, dirs, files in os.walk(rootdir):
        for file in files:
            if file.endswith(ext):
                f.write(os.path.join(subdir,file)+"\n")
f.close()

open_file('/home/prateek/Avicii - The Nights.mp4')
