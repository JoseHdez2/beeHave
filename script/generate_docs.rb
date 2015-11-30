PROJECT = 'beeHave'
# Ponerse en 'root' del proyecto.
Dir.getwd.split.concat("\/#{PROJECT}")
i_see_src_folder = false
Dir["./"].each do |file| { i_see_src_folder = true if file == 'src' }
if not i_see_src_folder Dir.chdir("")

class FolderFile
  attr_reader :type
  attr_accessor :data

  def __init__(data)
    @data = data
    if data.class.name == Array
      @type = :folder
    elsif data.class.name == String
      @type = :file
    else
      throw "Solo Folder o File!"
  end

  def push(data)
    if data.class.name == FolderFile and @type == :folder
      self.data.push(data)
  end

  def set(data)
    if data.class.name == Fo
    elsif data
    else
  end
end

def getSourceFiles
  Dir["src/"].each do |file|
    File.open(file, "rb").read
  end
end
