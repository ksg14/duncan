#Python3

import os
import subprocess, sys
import webbrowser
#Rootdir for windows to figure out is remaining

#ext = ('.mp4','.avi','.webm','.mp3')
ext = ('.mp3')
indexfile_name = 'songs.index'

if sys.platform == 'win64':
    rootdir = 'C:\\'    
else:
    rootdir = '/home/'



def indexing():
    with open(indexfile_name, "w") as f:        
        for subdir, dirs, files in os.walk(rootdir):
            for file in files:
                if file.endswith(ext):
                    f.write(os.path.join(subdir,file)+'\t'+ str(0)+ "\n")


if __name__ == '__main__':
    indexing()