import sys

news_links = {
    'india': 'https://news.google.co.in/news/section?cf=all&pz=1&ned=in&topic=n&siidp=f972be75a73f645d3b28154d7d23764de320&ict=ln&ar=1492488183'
    ,'entertainment':'https://news.google.co.in/news/section?cf=all&pz=1&ned=in&topic=e&siidp=564e6eaddfd79ccf073ed5b3919f09f6aa81&ict=ln'
    ,'technology':'https://news.google.co.in/news/section?cf=all&pz=1&ned=in&topic=tc&siidp=a2dedf3e6af7ca6810934e01e2e2f8cb4bdc&ict=ln'
    ,'sports': 'https://news.google.co.in/news/section?cf=all&pz=1&ned=in&topic=s&siidp=81df78dd038ac92b6c7be10bb3ae13d882c7&ict=ln'
    ,'science':'https://news.google.co.in/news/section?cf=all&pz=1&ned=in&topic=snc&siidp=79989e9366ff82e6d289a0756ce9664bb429&ict=ln'
}



def read_file():
    data = []
    with open('news.txt','r') as f:
        for line in f.readlines():
            x = [i.strip() for i in line.split('\t')]
            x[1] = int(x[1])
            data.append(x)
    return data

def count_increase(category_name,data):
    print(data)
    for pos,i,j in enumerate(data):
        if i == category_name:
            data[pos][1] += 1
            data[pos][1] = str(data[pos][1])
    print(data)
    with open('news.txt','w') as f:
        f.writelines(data)
     

if __name__ == '__main__':
    
    data = read_file()
    if(len(sys.argv) == 1):
        sorted_list = sorted(data,key = lambda t:t[1] , reverse=True)
        final_link = news_links[sorted_list[0][0]]
    else:
        category = sys.argv[1]
        final_link = news_links[category]
    
    #count_increase('science',data)

    with open('./src/main/java/app/news/output_link.txt','w') as f:
        f.writelines(final_link)