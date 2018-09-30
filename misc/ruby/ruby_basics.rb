# Available scenarios for ruby basics
def scenarios() [
# Scenario 1
[	
	"Print hello world", 
	lambda {
		print "Hello world!\n\n"  
		[
			"x   x",
			"",
			"|___|"
		].each { |item| puts item }
	}
],
	
# Scenario 2
[
	"Pyramid", 
	lambda {
		repeat = 20
		repeat.times do |x| 
			puts (" " * (repeat - x / 2 )) + ("." * x)
		end
	}
], 

# Scenario 3
[
	"parametrized text, text array, array sum", 
	lambda {
		puts "Print URLs for: Google Yahoo MSN"
		%w(Google Yahoo MSN).map do |engine|
			print "\t"
			puts "http://www.#{engine.downcase}.com"
		end		
		
		puts "\n2 * sum 1..3 = #{[1, 2, 3].map{|e| e * 2}. sum.to_s}"
	}
],

# Scenario 4
[
	"Read all lines in 'ruby_basics.rb' script and print them to screen", 
	lambda {
		File.readlines('ruby_basics.rb').each do |line|
			puts line
		end
	}
],

# Scenario 5
[
	"", 
	lambda {
		
	}
]
] end

# Scenario runner
scenario_index = 0
scenarios.each do |scenario| 
	scenario_name = scenario[0]
	puts "#{scenario_index}.) #{scenario_name}"
	scenario_index += 1
end
choice = gets.to_i
scenarios()[choice][1].call


gets

