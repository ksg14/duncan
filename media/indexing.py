#Python3

import os
import subprocess, sys

#Rootdir for windows to figure out is remaining
rootdir = '/home/'
ext = ('.mp4','.avi')

indexfile_name = 'songs.index'


#opening media by passing in the whole path of song works for both Windows and Linux
def open_file(filename):
    if sys.platform == "win64":
        os.startfile(filename)
    else:
        opener ="open" if sys.platform == "darwin" else "xdg-open"
        subprocess.call([opener, filename])


def retrieve_songs_with_count():
    name_count_tuples_list = []
    with open(indexfile_name, "r") as f:        
        for line in f.readlines():
            x = [i.strip() for i in line.split('/')]
            song_name_with_count = [i.strip() for i in x[-1].split('\t')]
            name_count_tuples_list.append((song_name_with_count[0] , song_name_with_count[1]))
    return name_count_tuples_list

# where indexing of .mp4 and .avi files is done
def indexing():
    with open(indexfile_name, "w") as f:        
        for subdir, dirs, files in os.walk(rootdir):
            for file in files:
                if file.endswith(ext):
                    f.write(os.path.join(subdir,file)+'\t'+ str(0)+ "\n")

def path_to_song(song_pos):
    with open(indexfile_name,'r') as f:
        y = f.readlines()[song_pos].split('\t')
        return y[0].strip()

def search_song(song_list,name):
    for i,(n,c) in enumerate(song_list):
    




indexing()

print('Enter the song you want to search for?? \n')
x = input()

name_count_tuples_list = retrieve_songs_with_count()

#search_song(name_count_tuples_list,x)

#print(name_count_tuples_list)

# open_file('/home/prateek/Avicii - The Nights.mp4')
