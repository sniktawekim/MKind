#define scr_particle


particle1 = part_type_create();
part_type_shape(particle1,pt_shape_explosion);
part_type_size(particle1,0.10,0.44,0,0);
part_type_scale(particle1,2.44,3.63);
part_type_color3(particle1,12335967,4961927,9507590);
part_type_alpha3(particle1,0.59,0.60,0.02);
part_type_speed(particle1,5.00,7.78,-0.02,0);
part_type_direction(particle1,0,359,-1,0);
part_type_gravity(particle1,0,270);
part_type_orientation(particle1,0,0,0,0,1);
part_type_blend(particle1,1);
part_type_life(particle1,47,48);

emitter1 = part_emitter_create(Sname);
part_emitter_region(Sname,emitter1,x,x,y,y,0,0);
part_emitter_stream(Sname,emitter1,particle1,5);


