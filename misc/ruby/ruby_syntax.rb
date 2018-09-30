# single line ruby comment

=begin
	----------------------
	multiline ruby comment
	----------------------
=end 

puts "Ruby scripts can be run using 'ruby' tool."
puts <<-MULTILINE_STRING

'irb' (Interactive Ruby Shell) is a standard REPL tool
REPL (Read-Eval-Print-Loop) is a simple interactive computer programming 
environment that takes a single input, evaluates it and returns a result 
to the user. It's very useful for learning and experimenting with language 
features. 
MULTILINE_STRING

# Everything is an object
22.class 		# => Integer
0b000011.class  # => Integer
0xf.class		# => Integer
0.3.class 		# => Float
'...'.class 	# => String
{}.class		# => Hash
[].class		# => Array
//.class		# => Regexp
:value			# => Symbol

# Arithmetic operations
2 + 2			# 4
10 - 1			# 9
2 * 3			# 6
2 ** 3			# 8
3 / 10			# 0
3.0 / 10		# 0.3

# Type conversion
1.to_f				# 1.0
'11'.to_s			# 11
:a.to_s				# "s"
43.to_s				# "43"

print "\nPress any key to continue..."
gets 