rails g model plant 
	accepted_symbol:string
	synonym_symbol:string
	scientific_name:string 
	common_name:string
	state:string
	duration:string
	growth_habit:string
	native_status:string
	growth_period:string
	flower_color:string
	flower_conspicuous:boolean
	height_mature:string
	lifespan:string
	drought_tolerance:string
	shade_tolerance:string
	bloom_period:string

f = File.open("plantinfo.txt")
plant = f.split("\n")
plant.each do |p|
	p.split!(",")
	Plant.new(
		accepted_symbol:p[0],
		synonym_symbol:p[1],
		scientific_name:p[2],
		common_name:p[3],
		state:p[4],
		duration:p[5],
		growth_habit:p[6],
		native_status:p[7],
		growth_period:p[8],
		flower_color:p[9],
		flower_conspicuous:p[10],
		height_mature:p[11],
		lifespan:p[12],
		drought_tolerance:p[13],
		shade_tolerance:p[14],
		bloom_period:p[15]
	).save()

end

