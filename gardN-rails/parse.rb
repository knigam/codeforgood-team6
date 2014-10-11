#rails g model plant accepted_symbol:string synonym_symbol:string scientific_name:string common_name:string state:string duration:string growth_habit:string native_status:string growth_period:string flower_color:string flower_conspicuous:boolean height_mature:string lifespan:string  drought_tolerance:string shade_tolerance:string bloom_period:string

File.open("plantinfo.txt", "r").each_line do |p|
    p = p.split("\",\"")
    plant = Plant.new(
            accepted_symbol:p[0],
            synonym_symbol:p[1],
            scientific_name:p[2],
            common_name:p[3],
            duration:p[5],
            growth_habit:p[6],
            growth_period:p[8],
            flower_color:p[9],
            flower_conspicuous:p[10],
            height_mature:p[11],
            lifespan:p[12],
            drought_tolerance:p[13],
            shade_tolerance:p[14],
            bloom_period:p[15]
    )
    p[4].split(", ").each do |s|
        if s[-1] == ")"
            n = s[-3..-2]
        else
            n = s[-2..-1]
        end
        plant.states.build(name: n)
    end
    plant.save
end
