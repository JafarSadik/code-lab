require 'sinatra'

get '/' do
	if params.has_key?(:file)
		File.read(params[:file])
		.gsub(/\n|\r|\n\r/, '<br/>')
		.gsub(/\t/, '    ')
		.gsub(/\s/, '&nbsp;')
	else 
		redirect "/?file=ruby_basics.rb"
	end
end
