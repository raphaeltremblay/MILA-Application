%Setting up all the parameters in the two ecosystems (x & y) of our
%differential equation system and storing them under p
p.Ix=0.1;p.Iy=0.1;
p.eN=0.1;p.eD=0.1;
p.mP=0.2;p.mH=0.2;
p.alphax=0;p.alphay=1;p.betax=1;p.betay=1;
p.r=0.2;
p.Bx=0;p.By=0;p.k=0.5;p.Pxmax=1;p.Pymax=1;
p.hx=0.25;p.hy=0.25;
p.dN=0.1;p.dD=0;p.dH=0;p.dP=0;
tspan=[0 1000];
%Setting up the starting population for all groups (N: nutrient,
%D:detritus, H:herbivores, P:primary producers) at time t=0
%init=[N_x N_y P_x P_y H_x H_y D_x D_y]
init=[1 1 0.1 0.1 0.01 0.01 0.1 0.1];
matrix=zeros(1000,5);
opts = odeset('NonNegative',1:8)
%In this example of a code, we are evaluating the end population of each
%group after 2000 iterations, each with an increasingly high recycling rate
for i=1:2000
  matrix(i,1)=p.alphax;
  [t,x]=ode45(@(t,x) MyModelFx(t,x,p),tspan,init,opts);
  matrix(i,2)=x(end,1);
  matrix(i,3)=x(end,3);
  matrix(i,4)=x(end,5);
  matrix(i,5)=x(end,7);
  p.alphax=p.alphax+0.001;
  
end
%Plotting each end population point for all four groups (in different
%colours) against the value of the recycling rate
figure(1)
plot(matrix(:,1),matrix(:,2),'y',matrix(:,1),matrix(:,3),'g',matrix(:,1),matrix(:,4),'r',matrix(:,1),matrix(:,5),'k')
xlabel("Recycling Rate")
