PROJECT = 'beeHave'
# Ponerse en 'root' del proyecto.
Dir.getwd.split.concat("\/#{PROJECT}")
i_see_src_folder = false
Dir["./"].each do |file| { i_see_src_folder = true if file == 'src' }
if not i_see_src_folder throw "No 'src' folder in project root"

def getSourceFiles
  Dir["src/"].each do |file|
    File.open(file, "rb").read
  end
end

# 
#
def make_doc_file(src_filepath)

end

# pass the string content of a Java source file
# get the string content of its equivalent doc file
def make_doc_content(src_cont)
  doc_cont = ""

end
