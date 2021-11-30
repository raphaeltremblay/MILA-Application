%Setting up all the parameters in the two ecosystems (x & y) of our
%differential equation system and storing them under p
p.Ix=0.1;p.Iy=0.1;
p.eN=0.1;p.eD=0.1;
p.mP=0.2;p.mH=0.2;
p.alphax=1;p.alphay=1;p.betax=1;p.betay=1;
p.r=0.2;
p.Bx=0;p.By=0;p.k=0.5;p.Pxmax=100;p.Pymax=100;
p.dN=0.1;p.dD=0;p.dH=0;p.dP=0;
tspan=[0 1000];
%Setting up the starting population for all groups (N: nutrient,
%D:detritus, H:herbivores, P:primary producers) at time t=0
%init=[N_x N_y P_x P_y H_x H_y D_x D_y]
init=[1 1 0.01 0.01 0.01 0.01 0 0];
matrix=zeros(10,9);
opts = odeset('NonNegative',1:8)
%Plotting the end population values for each group, separating the
%ecosystem x and y, all for set parameters
  [t,x]=ode45(@(t,x) MyModelFx2(t,x,p),tspan,init,opts);
figure(1)
plot(t,x(:,1),'g',t,x(:,3),'k',t,x(:,5),'r',t,x(:,7),'y')
figure(2)
plot(t,x(:,2),'g',t,x(:,4),'k',t,x(:,6),'r',t,x(:,8),'y')