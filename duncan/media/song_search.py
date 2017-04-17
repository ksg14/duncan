#Python3
import sys
import os
import subprocess, sys
import webbrowser
#Rootdir for windows to figure out is remaining

#ext = ('.mp4','.avi','.webm','.mp3')
ext = ('.mp3'   )
indexfile_name = 'songs.index'

if sys.platform == 'win64':
    rootdir = 'C:\\'    
else:
    rootdir = '/home/'

#opening media by passing in the whole path of song works for both Windows and Linux
def open_file(filename):
    if sys.platform == "win64":
        os.startfile(filename)
    else:
        opener ="open" if sys.platform == "darwin" else "xdg-open"
        subprocess.Popen([opener, filename])


def retrieve_songs_with_count():
    name_count_tuples_list = []
    pos = 0
    with open(indexfile_name, "r") as f:        
        for line in f.readlines():
            x = [i.strip() for i in line.split('/')]
            song_name_with_count = [i.strip() for i in x[-1].split('\t')]
            name_count_tuples_list.append((pos,song_name_with_count[0].lower() , int(song_name_with_count[1])))
            pos += 1
    return name_count_tuples_list

def path_to_song(song_pos):
    with open(indexfile_name,'r') as f:
        y = f.readlines()[song_pos].split('\t')
        return y[0].strip()

def search_song(song_list,name):
    s_index = []
    for (i,n,c) in song_list:
        if name in n:
            s_index.append((i,c))
    sorted_by_count = sorted(s_index,key=lambda t:t[1] , reverse=True)
    return sorted_by_count

def count_increase(r):
    with open(indexfile_name,'r') as f:
        data = f.readlines()
    #new_count = int(data[r].split('\t')[1].strip()) + 1
    q = [i.strip() for i in data[r].split('\t')]
    data[r] =  q[0] + '\t'+ str(int(q[1]) + 1) + '\n'
    with open(indexfile_name,'w') as f:
        f.writelines(data)

def checking_counts(res):
    count_increase(res[0])
    open_file(path_to_song(res[0]))
    

def find_fav(count_list):
    sorted_list = sorted(count_list,key = lambda t:t[2] , reverse=True)
    return sorted_list[0]
    

if __name__ == '__main__':

    name_count_tuples_list = retrieve_songs_with_count()
    
    if len(sys.argv) == 1:
        song_pos = find_fav(name_count_tuples_list)
        #count_increase(song_pos)
        print(song_pos)
        checking_counts(song_pos)
        
    else:
        x = [sys.argv[i] for i in range(1,len(sys.argv))]
        song_name = ' '.join(x)
        res = search_song(name_count_tuples_list,song_name)
        print(res)
        if len(res):
            checking_counts(res[0])
        else:
            webbrowser.open('https://www.youtube.com/results?search_query=' + song_name)

